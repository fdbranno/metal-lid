package com.github.radtin.metallid.domain

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = DatabaseConfiguration::class, name = "databaseConfiguration"))
abstract class TestConfiguration {
    abstract fun name(): String
}

class DefaultConfiguration: TestConfiguration() {
    override fun name() = "Default"
}