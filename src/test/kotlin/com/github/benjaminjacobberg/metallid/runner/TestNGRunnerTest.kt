package com.github.benjaminjacobberg.metallid.runner

import com.github.benjaminjacobberg.metallid.datasource.DataSource
import com.github.benjaminjacobberg.metallid.datasource.YamlFileDataSource
import org.junit.Test

import java.io.File

class TestNGRunnerTest {

    private val dataSource: DataSource = YamlFileDataSource(File("src/test/resources/test.yaml"))
    private val runner: Runner = TestNGRunner(dataSource)

    @Test
    fun run() {
        runner.run()
    }
}