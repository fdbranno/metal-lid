package com.github.radtin.metallid.action

import com.github.radtin.metallid.configuration.DatabaseConfiguration
import com.github.radtin.metallid.domain.Input
import com.github.radtin.metallid.domain.Output
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.util.*
import kotlin.collections.HashMap

class DatabaseAction {
    private val log = LoggerFactory.getLogger(this::class.qualifiedName)!!

    fun executeQuery(input: Input): Output {
        val configuration = input.configuration as? DatabaseConfiguration
                ?: throw ActionException("Input configuration must implement DatabaseConfiguration.")

        var outputString = ""
        var connection: Connection? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null
        try {
            connection = configuration.openConnection()
            statement = connection.createStatement()
            resultSet = statement.executeQuery(input.value)

            val resultSetMetaData = resultSet.metaData
            val headers: MutableList<Any> = ArrayList()
            for (i in 1..resultSetMetaData.columnCount) {
                headers.add(resultSetMetaData.getColumnLabel(i))
            }

            val outputList: MutableList<Any> = ArrayList()
            while (resultSet.next()) {
                val outputMap: MutableMap<String, String> = HashMap()
                for (i in 1..resultSetMetaData.columnCount) {
                    outputMap[headers[i - 1].toString()] = resultSet.getObject(i).toString()
                }
                outputList.add(outputMap.toMutableMap())
                outputMap.clear()
            }
            outputString = outputList.toString()
        } catch (e: Exception) {
            log.error(e.message, e)
        } finally {
            try {
                connection?.close()
            } catch (e: Exception) {
                log.error("Failed to close connection!")
            }
            try {
                statement?.close()
            } catch (e: Exception) {
                log.error("Failed to close statement!")
            }
            try {
                resultSet?.close()
            } catch (e: Exception) {
                log.error("Failed to close result set!")
            }
        }

        log.debug("DatabaseAction.executeQuery result equals ({}).", outputString)

        return Output(outputString)
    }

}