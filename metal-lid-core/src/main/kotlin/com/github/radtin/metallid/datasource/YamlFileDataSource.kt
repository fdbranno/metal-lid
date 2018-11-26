package com.github.radtin.metallid.datasource

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.radtin.metallid.domain.TestSuite
import com.github.radtin.metallid.domain.TestSuiteConfiguration
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class YamlFileDataSource(private val suiteSource: File, private val configSource: File) : DataSource {

    override fun getData(): TestSuite {
        val inputStream: InputStream = FileInputStream(suiteSource)
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())

        return mapper.readValue(inputStream, TestSuite::class.java)
    }

    override fun getConfigs(): TestSuiteConfiguration {
        val inputStream: InputStream = FileInputStream(configSource)
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())
        return mapper.readValue(inputStream, TestSuiteConfiguration::class.java)
    }

}