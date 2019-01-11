package com.github.radtin.metallid.report

import org.junit.Test

class ConsoleReportTest {

    private var report = TestReportSuiteGenerator.getTestReportSuite()

    @Test
    fun console_report_test() {
        ConsoleReport(report).outputResults()
    }

    @Test
    fun detailed_report_test() {
        val properties = mutableMapOf<String, String>()
        properties["detailed"] = "true"
        ConsoleReport(report, properties).outputResults()
    }
}