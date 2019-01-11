package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.report.ReportScenario
import com.github.radtin.metallid.domain.report.ReportStep
import com.github.radtin.metallid.domain.report.ReportSuite

import java.io.File

class CsvReport(private val reportSuite: ReportSuite,
                val properties: MutableMap<String, String>? = null) : MetalLidReport(reportSuite, properties) {

    private var propertiesMap = applyProperties()

    override fun outputResults() {
        var content = "Test Scenario,Test Step,Status,Output\n"
        for (scenario: ReportScenario in reportSuite.scenarios) {
            for (step: ReportStep in scenario.steps) {
                content = content.plus("${scenario.name},${step.name},${status(step.output)},${results(step.output)}\n")
            }
        }

        val file = File("test-output/${propertiesMap["filename"]}.csv")
        file.createNewFile()
        file.writeText(content)
    }
}