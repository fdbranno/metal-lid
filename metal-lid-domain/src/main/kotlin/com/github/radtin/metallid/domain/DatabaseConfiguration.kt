package com.github.radtin.metallid.domain

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import javax.sql.DataSource

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(value = OracleDatabaseConfiguration::class, name = "oracleDatabaseConfiguration"),
        JsonSubTypes.Type(value = PostgresDatabaseConfiguration::class, name = "postgresDatabaseConfiguration"))
abstract class DatabaseConfiguration : TestConfiguration() {
    abstract fun datasource(): DataSource
}