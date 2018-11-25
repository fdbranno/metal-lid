package com.github.radtin.metallid.action

import com.github.radtin.metallid.configuration.PostgresqlConfiguration
import com.github.radtin.metallid.domain.Input
import com.github.radtin.metallid.domain.configuration.Database
import com.opentable.db.postgres.embedded.EmbeddedPostgres
import org.testng.annotations.Test

import org.testng.Assert.*
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

class DatabaseActionTest {

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
    fun testExecuteQuery() {
        val databaseAction = DatabaseAction()
        databaseAction.executeQuery(Input("" +
                "SELECT *\n" +
                "  FROM (VALUES ('row1-1', 'row1-2'),\n" +
                "               ('row2-1', 'row2-2')) AS q (column1, column2)",
                postgresqlConfiguration))
    }
}