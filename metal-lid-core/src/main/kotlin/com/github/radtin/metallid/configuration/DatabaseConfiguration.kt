package com.github.radtin.metallid.configuration

import java.sql.Connection

interface DatabaseConfiguration {

    fun openConnection(): Connection

    fun closeConnection(connection: Connection)

}