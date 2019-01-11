package com.github.radtin.metallid.report

import org.junit.Test

class HtmlReportTest {

    private var report = TestReportSuiteGenerator.getTestReportSuite()

    @Test
    fun html_report_test() {
        HtmlReport(report).outputResults()
    }

    @Test
    fun change_filename_test() {
        val properties = mutableMapOf<String, String>()
        properties["filename"] = "New File"
        HtmlReport(report, properties).outputResults()
    }

    @Test
    fun detailed_report_test() {
        val properties = mutableMapOf<String, String>()
        properties["detailed"] = "true"
        HtmlReport(report, properties).outputResults()
    }
}