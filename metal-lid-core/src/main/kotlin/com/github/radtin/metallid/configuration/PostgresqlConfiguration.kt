package com.github.radtin.metallid.configuration

import com.github.radtin.metallid.domain.configuration.Database
import java.sql.Connection
import java.sql.DriverManager


class PostgresqlConfiguration(private val database: Database) : DatabaseConfiguration {

    init {
        val qualifiedName: String? = org.postgresql.Driver::class.qualifiedName
        Class.forName(qualifiedName)
    }

    override fun openConnection(): Connection {
        return DriverManager.getConnection(database.url, database.username, database.password)
    }

    override fun closeConnection(connection: Connection) {
        connection.close()
    }

}