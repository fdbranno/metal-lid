package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.report.ReportOutput
import com.github.radtin.metallid.domain.report.ReportScenario
import com.github.radtin.metallid.domain.report.ReportStep
import com.github.radtin.metallid.domain.report.ReportSuite
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

internal object TestReportSuiteGenerator {

     fun getTestReportSuite(): ReportSuite {
        val output1 = ReportOutput("error", IllegalArgumentException("Invalid character 'T'"))
        val step1 = ReportStep("Test Step 1", "class.name.stuff", "methodThatDoesStuff", "4", output1)
        val output2 = ReportOutput("meh", NullPointerException("Null reference on line 72"))
        val step2 = ReportStep("Test Step 2", "class.name.stuff", "methodThatDoesStuff", "asdf", output2)
        val scenario1 = ReportScenario("Test Scenario 1", listOf(step1, step2))

        val output3 = ReportOutput("words and stuff", null)
        val step3 = ReportStep("Test Step 1", "class.name.stuff", "methodThatDoesStuff", "inspresasut", output3)
        val output4 = ReportOutput("more test results", null)
        val step4 = ReportStep("Test Step 2", "class.classes.className.class", "newMethod", "input", output4)
        val output5 = ReportOutput("blah blah blah", IndexOutOfBoundsException("Index 7 out of bounds"))
        val step5 = ReportStep("Test Step 3", "class.name.thing.whatchamacallit.thingy.dijuridoo", "methodThatDoesStuff", "94", output5)
        val scenario2 = ReportScenario("Test Scenario 2", listOf(step3, step4, step5))

        val output6 = ReportOutput("successful test", null)
        val step6 = ReportStep("Test Step 1", "class.name.MoreStuff", "anotherMethod", "a2", output6)
        val scenario3 = ReportScenario("Test Scenario 3", listOf(step6))

        return ReportSuite("Test Suite", listOf(scenario1, scenario2, scenario3))
    }
}