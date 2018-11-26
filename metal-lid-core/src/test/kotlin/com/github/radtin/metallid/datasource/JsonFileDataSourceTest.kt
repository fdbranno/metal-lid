package com.github.radtin.metallid.datasource

import org.junit.Assert
import org.junit.Test
import java.io.File

class JsonFileDataSourceTest {

    @Test
    fun json_test() {
        val excelDataSource: DataSource = JsonFileDataSource(File("src/test/resources/test.json"),
                File("src/test/resources/config.json"))
        val expected = "TestSuite(name=Test Suite, testScenarios=[TestScenario(name=Test Scenario 1, testSteps=[TestStep(name=Test Step 1, className=System, methodName=printlnMessage, value=IT'S ALIVE!), TestStep(name=Test Step 2, className=System, methodName=printlnMessage, value=IT DID IT AGAIN!), TestStep(name=Test Step 3, className=System, methodName=printlnMessage, value=IT DID IT A THIRD TIME!)]), TestScenario(name=Test Scenario 2, testSteps=[TestStep(name=Test Step 1, className=System, methodName=printlnMessage, value=IT'S ALIVE!), TestStep(name=Test Step 2, className=System, methodName=printlnMessage, value=IT DID IT AGAIN!), TestStep(name=Test Step 3, className=System, methodName=printlnMessage, value=IT DID IT A THIRD TIME!)]), TestScenario(name=Test Scenario 3, testSteps=[TestStep(name=Test Step 1, className=System, methodName=printlnMessage, value=IT'S ALIVE!), TestStep(name=Test Step 2, className=System, methodName=printlnMessage, value=IT DID IT AGAIN!), TestStep(name=Test Step 3, className=System, methodName=printlnMessage, value=IT DID IT A THIRD TIME!)])])"
        val actual = excelDataSource.getData().toString()

        Assert.assertEquals(expected, actual)
    }

}