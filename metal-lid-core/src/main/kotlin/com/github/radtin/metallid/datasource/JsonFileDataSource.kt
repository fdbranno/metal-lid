package com.github.radtin.metallid.datasource

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.radtin.metallid.domain.Suite
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class JsonFileDataSource(private val source: File) : DataSource {

    override fun getData(): Suite {
        val inputStream: InputStream = FileInputStream(source)
        val mapper = jacksonObjectMapper()

        return mapper.readValue(inputStream)
    }

}