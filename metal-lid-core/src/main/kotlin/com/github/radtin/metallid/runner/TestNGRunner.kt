package com.github.radtin.metallid.runner

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.radtin.metallid.datasource.DataSource
import com.github.radtin.metallid.datasource.YamlFileDataSource
import com.github.radtin.metallid.domain.*
import org.testng.TestNG
import org.testng.annotations.Parameters
import org.testng.annotations.Test
import org.testng.xml.XmlClass
import org.testng.xml.XmlSuite
import org.testng.xml.XmlTest
import java.io.File
import java.util.regex.Pattern

class TestNGRunner(private val dataSource: DataSource) : Runner {

    override fun run() {
        val testNG = TestNG()
        testNG.setXmlSuites(mutableListOf(createXmlSuite(dataSource.getConfigs(), dataSource.getData())))
        testNG.run()
    }

}

/**
 * Executes handles the logic execution of test scenarios from the datasource.
 */
class TestClass {

    @Test
    @Parameters("testConfiguration", "testScenario")
    fun dynamicTest(testConfigJson: String, testCaseJson: String) {
        val values = mutableMapOf<String, Any>()
        val testSuiteConfiguration = unmarshalTestConfig(testConfigJson)
        val testConfigurations = getConfigMap(testSuiteConfiguration)
        val testScenario: TestScenario = unmarshalTestScenario(testCaseJson)
        for (testStep: TestStep in testScenario.testSteps) {
            val value = getInput(testStep.value, values)
            val configuration = getTestConfiguration(testConfigurations, testStep) as TestConfiguration
            values[testStep.output] = invokeMethod(testStep.className, testStep.methodName, configuration, value).value
        }
    }

    private fun getTestConfiguration(configMap: Map<String, Any>, testStep: TestStep) =
            if (configMap.containsKey(testStep.config)) configMap[testStep.config] else DefaultConfiguration()

    private fun getInput(stepVal: String, values: MutableMap<String, Any>): Any {
        val pattern: Pattern = Pattern.compile("^\\\$\\{.*\\}\$")
        val matcher = pattern.matcher(stepVal)
        return if (matcher.find() && values.contains(stepVal.substring(2, stepVal.length - 1))) {
            values[stepVal.substring(2, stepVal.length - 1)] as Any
        } else {
            stepVal
        }
    }

    /**
     * Run an external method through reflection.  The class and method will be specified from the datasource.
     */
    private fun invokeMethod(className: String, method: String, config: TestConfiguration, input: Any): Output {
        val sys = Class.forName(className)
        val met = sys.getMethod(method, TestConfiguration::class.javaObjectType, Any::class.javaObjectType)

        return met.invoke(sys.newInstance(), config, input) as Output
    }

    private fun getConfigMap(testSuiteConfiguration: TestSuiteConfiguration): Map<String, Any> {
        val configMap = mutableMapOf<String, Any>()

        for (testConfiguration: TestConfiguration in testSuiteConfiguration.testConfigurations) {
            configMap.put(testConfiguration.name(), testConfiguration)
        }

        return configMap
    }

}

/**
 * Constructs the XmlSuite that is required to execute the TestNG runner.
 */
fun createXmlSuite(testSuiteConfiguration: TestSuiteConfiguration, testSuite: TestSuite): XmlSuite {
    val xmlSuite = XmlSuite()
    xmlSuite.name = testSuite.name

    for (testScenario: TestScenario in testSuite.testScenarios) {
        val xmlTest = XmlTest(xmlSuite)
        xmlTest.name = testScenario.name
        xmlTest.xmlClasses = mutableListOf(XmlClass(TestClass()::class.java))
        xmlTest.addParameter("testConfiguration", marshallTestConfig(testSuiteConfiguration))
        xmlTest.addParameter("testScenario", marshallTestScenario(testScenario))
    }

    return xmlSuite
}

/**
 * Unmarshall String to TestScenario.  (This method was created because you can't pass an object as a parameter in TestNG.)
 */
private fun unmarshalTestScenario(testScenarioString: String): TestScenario {
    val mapper = jacksonObjectMapper()
    return mapper.readValue(testScenarioString)
}

/**
 * Marshall TestScenario to String.  (This method was created because you can't pass an object as a parameter in TestNG.)
 */
private fun marshallTestScenario(testScenario: TestScenario): String {
    val mapper = jacksonObjectMapper()
    return mapper.writeValueAsString(testScenario)
}

/**
 * Unmarshall String to TestSuiteConfiguration.  (This method was created because you can't pass an object as a parameter in TestNG.)
 */
private fun unmarshalTestConfig(testConfigString: String): TestSuiteConfiguration {
    val mapper = jacksonObjectMapper()
    mapper.configure(MapperFeature.INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES, false)
    return mapper.readValue(testConfigString)
}

/**
 * Marshall TestSuiteConfiguration to String.  (This method was created because you can't pass an object as a parameter in TestNG.)
 */
private fun marshallTestConfig(testConfig: TestSuiteConfiguration): String {
    val mapper = jacksonObjectMapper()
    return mapper.writeValueAsString(testConfig)
}

/**
 * Main method to test out runner execution during development.
 */
private val dataSource: DataSource = YamlFileDataSource(File("metal-lid-core/src/test/resources/test.yaml"),
        File("metal-lid-core/src/test/resources/config.yaml"))
private val runner: Runner = TestNGRunner(dataSource)

fun main(args: Array<String>) {
    runner.run()
}