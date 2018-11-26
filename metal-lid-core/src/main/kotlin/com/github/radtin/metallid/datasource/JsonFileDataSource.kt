package com.github.radtin.metallid.datasource

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.radtin.metallid.domain.TestSuite
import com.github.radtin.metallid.domain.TestSuiteConfiguration
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class JsonFileDataSource(private val suiteSource: File, private val configSource: File) : DataSource {

    override fun getData(): TestSuite {
        val inputStream: InputStream = FileInputStream(suiteSource)
        val mapper = jacksonObjectMapper()

        return mapper.readValue(inputStream)
    }

    override fun getConfigs(): TestSuiteConfiguration {
        val inputStream: InputStream = FileInputStream(configSource)
        val mapper = jacksonObjectMapper()

        return mapper.readValue(inputStream)
    }

}