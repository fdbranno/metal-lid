package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.Output
import com.github.radtin.metallid.domain.report.ReportScenario
import com.github.radtin.metallid.domain.report.ReportStep
import com.github.radtin.metallid.domain.report.ReportSuite
import org.junit.Test
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

class HtmlReportTest {

    private var report: ReportSuite

    init {
        val output1 = Output("error", IllegalArgumentException("Invalid character 'T'"))
        val step1 = ReportStep("Test Step 1", output1)
        val output2 = Output("meh", NullPointerException("Null reference on line 72"))
        val step2 = ReportStep("Test Step 2", output2)
        val scenario1 = ReportScenario("Test Scenario 1", listOf(step1, step2))

        val output3 = Output("words and stuff", null)
        val step3 = ReportStep("Test Step 1", output3)
        val output4 = Output("more test results", null)
        val step4 = ReportStep("Test Step 2", output4)
        val output5 = Output("blah blah blah", IndexOutOfBoundsException("Index 7 out of bounds"))
        val step5 = ReportStep("Test Step 3", output5)
        val scenario2 = ReportScenario("Test Scenario 2", listOf(step3, step4, step5))

        val output6 = Output("successful test", null)
        val step6 = ReportStep("Test Step 1", output6)
        val scenario3 = ReportScenario("Test Scenario 3", listOf(step6))

        report = ReportSuite("Test Suite", listOf(scenario1, scenario2, scenario3))
    }

    @Test
    fun html_report_test() {
        HtmlReport().outputResults(report)
    }
}