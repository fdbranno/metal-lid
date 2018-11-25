package com.github.radtin.metallid.configuration.database

import com.github.radtin.metallid.configuration.PostgresqlConfiguration
import com.github.radtin.metallid.domain.configuration.Database
import com.opentable.db.postgres.embedded.EmbeddedPostgres
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.sql.Connection


class PostgresqlConfigurationTest {

    lateinit var postgresqlConfiguration: PostgresqlConfiguration
    lateinit var embeddedPostgres: EmbeddedPostgres

    @BeforeMethod
    fun setUp() {
        embeddedPostgres = EmbeddedPostgres.start()
        val url = embeddedPostgres.getJdbcUrl("postgres", "postgres")
        postgresqlConfiguration = PostgresqlConfiguration(Database("Test Configuration", PostgresqlConfiguration::class.qualifiedName!!, url, "postgres", "postgres"))
    }

    @AfterMethod
    fun tearDown() {
        embeddedPostgres.close()
    }

    @Test
    fun testConnection() {
        val connection: Connection = postgresqlConfiguration.openConnection()
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT 1")
        postgresqlConfiguration.closeConnection(connection)

        assertTrue(resultSet.next())
        assertThat(resultSet.getInt(1), `is`(1))
        assertFalse(resultSet.next())
    }

}