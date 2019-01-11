package com.github.radtin.metallid.report

import org.junit.Test

class CsvReportTest {

    private var report = TestReportSuiteGenerator.getTestReportSuite()

    @Test
    fun csv_report_test() {
        CsvReport(report).outputResults()
    }

    @Test
    fun change_filename_test() {
        val properties = mutableMapOf<String, String>()
        properties["filename"] = "New File"
        CsvReport(report, properties).outputResults()
    }

    @Test
    fun detailed_report_test() {
        val properties = mutableMapOf<String, String>()
        properties["detailed"] = "true"
        CsvReport(report, properties).outputResults()
    }
}