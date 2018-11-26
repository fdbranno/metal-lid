package com.github.radtin.metallid.runner

import com.github.radtin.metallid.datasource.DataSource
import com.github.radtin.metallid.datasource.YamlFileDataSource
import org.junit.Assert
import org.testng.annotations.Test
import java.io.File

class TestNGRunnerTest {

    private val dataSource: DataSource = YamlFileDataSource(File("src/test/resources/test.yaml"),
            File("src/test/resources/config.yaml"))
    private val runner: Runner = TestNGRunner(dataSource)

    @Test
    fun testRun() {
        try {
            runner.run()
        } catch (e: Exception) {
            Assert.fail("An error has occurred while running TestNGRunner.")
        }
    }
}