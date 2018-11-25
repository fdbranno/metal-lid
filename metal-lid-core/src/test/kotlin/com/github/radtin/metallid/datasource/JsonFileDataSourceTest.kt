package com.github.radtin.metallid.datasource

import org.junit.Assert
import org.junit.Test
import java.io.File

class JsonFileDataSourceTest {

    @Test
    fun json_test() {
        val excelDataSource: DataSource = JsonFileDataSource(File("src/test/resources/test.json"))
        val expected = "Suite(name=Test Suite, scenarios=[Scenario(name=Test Scenario 1, steps=[Step(name=Test Step 1, className=com.github.radtin.metallid.action.DeveloperDebugAction, methodName=output, value=debug, output=response), Step(name=Test Step 2, className=com.github.radtin.metallid.action.DeveloperDebugAction, methodName=output, value=\${response}, output=response2), Step(name=Test Step 3, className=com.github.radtin.metallid.action.DeveloperDebugAction, methodName=output, value=\${response2}, output=null)]), Scenario(name=Test Scenario 2, steps=[Step(name=Test Step 1, className=com.github.radtin.metallid.action.DeveloperDebugAction, methodName=output, value=debug, output=response), Step(name=Test Step 2, className=com.github.radtin.metallid.action.DeveloperDebugAction, methodName=output, value=\${response}, output=response2), Step(name=Test Step 3, className=com.github.radtin.metallid.action.DeveloperDebugAction, methodName=output, value=\${response2}, output=null)]), Scenario(name=Test Scenario 3, steps=[Step(name=Test Step 1, className=com.github.radtin.metallid.action.DeveloperDebugAction, methodName=output, value=debug, output=response), Step(name=Test Step 2, className=com.github.radtin.metallid.action.DeveloperDebugAction, methodName=output, value=\${response}, output=response2), Step(name=Test Step 3, className=com.github.radtin.metallid.action.DeveloperDebugAction, methodName=output, value=\${response2}, output=null)])])"
        val actual = excelDataSource.getData().toString()

        Assert.assertEquals(expected, actual)
    }

}