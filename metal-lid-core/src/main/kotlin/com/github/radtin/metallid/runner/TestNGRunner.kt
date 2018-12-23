package com.github.radtin.metallid.runner

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.radtin.metallid.datasource.DataSource
import com.github.radtin.metallid.domain.*
import org.testng.TestNG
import org.testng.annotations.Parameters
import org.testng.annotations.Test
import org.testng.xml.XmlClass
import org.testng.xml.XmlSuite
import org.testng.xml.XmlTest
import java.util.Arrays.asList

class TestNGRunner(private val dataSource: DataSource) : Runner(dataSource) {

    override fun run() {
        val testNG = TestNG()
        val xmlSuite = createXmlSuite(dataSource.getData())

        configuration()

        testNG.setXmlSuites(asList(xmlSuite))
        testNG.run()
    }

    private fun createXmlSuite(suite: Suite): XmlSuite {
        val xmlSuite = XmlSuite()
        xmlSuite.name = suite.name

        for (scenario: Scenario in suite.scenarios) {
            val xmlTest = XmlTest(xmlSuite)
            xmlTest.name = scenario.name
            xmlTest.xmlClasses = mutableListOf(XmlClass(TestClass()::class.java))
            xmlTest.addParameter("scenario", marshall(scenario))
        }

        return xmlSuite
    }

    class TestClass {

        @Test
        @Parameters("scenario")
        fun dynamicTest(testCaseJson: String) {
            val scenario: Scenario = unmarshal(testCaseJson)
            runTestSteps(scenario)
        }

    }

}

private fun marshall(scenario: Scenario): String? {
    val mapper = jacksonObjectMapper()
    return mapper.writeValueAsString(scenario)
}

private fun unmarshal(testCaseJson: String): Scenario {
    val mapper = jacksonObjectMapper()
    return mapper.readValue(testCaseJson)
}