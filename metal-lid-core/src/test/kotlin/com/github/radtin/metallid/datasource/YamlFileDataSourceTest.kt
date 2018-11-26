package com.github.radtin.metallid.datasource

import org.junit.Assert
import org.junit.Test
import java.io.File

class YamlFileDataSourceTest {

    @Test
    fun yaml_test() {
        val excelDataSource: DataSource = YamlFileDataSource(File("src/test/resources/test.yaml"),
                File("src/test/resources/config.yaml"))
        val expected = "TestSuite(name=Test Suite, testScenarios=[TestScenario(name=Test Scenario 1, testSteps=[TestStep(name=Test Step 1, className=System, methodName=printlnMessage, value=IT'S ALIVE!), TestStep(name=Test Step 2, className=System, methodName=printlnMessage, value=IT DID IT AGAIN!), TestStep(name=Test Step 3, className=System, methodName=printlnMessage, value=IT DID IT A THIRD TIME!)]), TestScenario(name=Test Scenario 2, testSteps=[TestStep(name=Test Step 1, className=System, methodName=printlnMessage, value=IT'S ALIVE!), TestStep(name=Test Step 2, className=System, methodName=printlnMessage, value=IT DID IT AGAIN!), TestStep(name=Test Step 3, className=System, methodName=printlnMessage, value=IT DID IT A THIRD TIME!)]), TestScenario(name=Test Scenario 3, testSteps=[TestStep(name=Test Step 1, className=System, methodName=printlnMessage, value=IT'S ALIVE!), TestStep(name=Test Step 2, className=System, methodName=printlnMessage, value=IT DID IT AGAIN!), TestStep(name=Test Step 3, className=System, methodName=printlnMessage, value=IT DID IT A THIRD TIME!)])])"
        val actual = excelDataSource.getData().toString()

        Assert.assertEquals(expected, actual)
    }

}