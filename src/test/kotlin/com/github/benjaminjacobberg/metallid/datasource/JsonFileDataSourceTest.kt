package com.github.benjaminjacobberg.metallid.datasource

import org.junit.Assert
import org.junit.Test
import java.io.File

class JsonFileDataSourceTest {

    @Test
    fun json_test() {
        val excelDataSource: DataSource = JsonFileDataSource(File("src/test/resources/test.json"))
        val expected = "TestSuite(name=testSuite, testCases=[TestCase(name=testCase1, testSteps=[TestStep(name=testStep1, className=value1, methodName=value1, value=value1), TestStep(name=testStep2, className=value2, methodName=value2, value=value2), TestStep(name=testStep3, className=value3, methodName=value3, value=value3)]), TestCase(name=testCase2, testSteps=[TestStep(name=testStep1, className=value1, methodName=value1, value=value1), TestStep(name=testStep2, className=value2, methodName=value2, value=value2), TestStep(name=testStep3, className=value3, methodName=value3, value=value3)]), TestCase(name=testCase3, testSteps=[TestStep(name=testStep1, className=value1, methodName=value1, value=value1), TestStep(name=testStep2, className=value2, methodName=value2, value=value2), TestStep(name=testStep3, className=value3, methodName=value3, value=value3)])])"
        val actual = excelDataSource.getData().toString()

        Assert.assertEquals(expected, actual)
    }

}