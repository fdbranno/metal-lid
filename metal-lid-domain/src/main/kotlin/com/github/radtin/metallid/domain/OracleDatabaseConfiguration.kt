package com.github.radtin.metallid.domain

import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

class OracleDatabaseConfiguration(val name: String,
                                  val hostname: String,
                                  val port: String,
                                  val serviceName: String,
                                  val username: String,
                                  val password: String) : DatabaseConfiguration() {
    override fun datasource(): DataSource {
        val dataSource = HikariDataSource()
        dataSource.maximumPoolSize = 100;
        dataSource.driverClassName = "oracle.jdbc.OracleDriver";
        dataSource.jdbcUrl = "jdbc:oracle:thin:@${hostname}:${port}:${serviceName}";
        dataSource.username = username
        dataSource.password = password

        return dataSource
    }

    override fun name() = name
}
