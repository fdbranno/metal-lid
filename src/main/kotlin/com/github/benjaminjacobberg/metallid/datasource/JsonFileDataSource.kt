package com.github.benjaminjacobberg.metallid.datasource

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.benjaminjacobberg.metallid.datasource.data.TestSuite
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

@Component
class JsonFileDataSource(private val source: File) : DataSource {

    override fun getData(): TestSuite {
        val inputStream: InputStream = FileInputStream(source)
        val mapper = jacksonObjectMapper()

        return mapper.readValue(inputStream)
    }

}