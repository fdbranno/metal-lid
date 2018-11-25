package com.github.radtin.metallid.datasource

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.radtin.metallid.domain.Suite
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class YamlFileDataSource(private val source: File) : DataSource {

    override fun getData(): Suite {
        val inputStream: InputStream = FileInputStream(source)
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())

        return mapper.readValue(inputStream, Suite::class.java)
    }

}