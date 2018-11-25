package com.github.benjaminjacobberg.metallid.datasource

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.benjaminjacobberg.metallid.datasource.data.TestSuite
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

@Component
class YamlFileDataSource(private val source: File) : DataSource {

    override fun getData(): TestSuite {
        val inputStream: InputStream = FileInputStream(source)
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())

        return mapper.readValue(inputStream, TestSuite::class.java)
    }

}