package com.github.radtin.metallid.runner

import com.github.radtin.metallid.datasource.DataSource
import com.github.radtin.metallid.datasource.YamlFileDataSource
import org.junit.Test
import java.io.File

class TestNGRunnerTest {
    private val dataSource: DataSource = YamlFileDataSource(File("src/test/resources/test.yaml"))
    private val runner: Runner = TestNGRunner(dataSource)

    @Test
    fun testRun() {
        runner.run()
    }
}