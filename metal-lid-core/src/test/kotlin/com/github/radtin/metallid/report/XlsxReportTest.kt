package com.github.radtin.metallid.report

import org.junit.Test

class XlsxReportTest {

    private var report = TestReportSuiteGenerator.getTestReportSuite()

    @Test
    fun xls_report_test() {
        XlsxReport(report).outputResults()
    }

    @Test
    fun change_filename_test() {
        val properties = mutableMapOf<String, String>()
        properties["filename"] = "New File"
        XlsxReport(report, properties).outputResults()
    }

    @Test
    fun detailed_report_test() {
        val properties = mutableMapOf<String, String>()
        properties["detailed"] = "true"
        XlsxReport(report, properties).outputResults()
    }
}