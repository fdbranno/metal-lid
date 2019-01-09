package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.report.ReportScenario
import com.github.radtin.metallid.domain.report.ReportStep
import com.github.radtin.metallid.domain.report.ReportSuite
import java.io.File

class CsvReport : MetalLidReport(null, null) {

    override fun applyProperties() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun outputResults(report: ReportSuite) {
        var content = "Test Scenario,Test Step,Status,Output\n"
        for (scenario: ReportScenario in report.scenarios) {
            for (step: ReportStep in scenario.steps) {
                content = content.plus(scenario.name).plus(",").plus(step.name).plus(",").plus(status(step.output)).plus(",").plus(results(step.output)).plus("\n")
            }
        }

        val file = File("test-output/".plus(report.name.trim().replace(" ", "_")).plus(".csv"))
        file.createNewFile()
        file.writeText(content)
    }
}