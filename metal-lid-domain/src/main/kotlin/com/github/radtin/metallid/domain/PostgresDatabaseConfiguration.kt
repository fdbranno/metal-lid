package com.github.radtin.metallid.domain

import javax.sql.DataSource

class PostgresDatabaseConfiguration(val name: String, val hostname: String, val port: String, val serviceName: String,
                                    val username: String = "", val password: String = "") : DatabaseConfiguration() {
    override fun datasource(): DataSource {
        TODO("not implemented")
    }

    override fun name() = name
}