package com.github.radtin.metallid.action

import com.github.radtin.metallid.domain.DatabaseConfiguration
import com.github.radtin.metallid.domain.Output
import com.github.radtin.metallid.domain.TestConfiguration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

class DBUtil {

    fun findAll(dbConfig: TestConfiguration, value: Any): Output {
        val databaseConfiguration: DatabaseConfiguration = dbConfig as DatabaseConfiguration
        val dataSource: DataSource = databaseConfiguration.datasource()
        val output: MutableList<MutableMap<String, Any>> = JdbcTemplate(dataSource).queryForList("SELECT * FROM ${value}")

        return Output(output)
    }

}