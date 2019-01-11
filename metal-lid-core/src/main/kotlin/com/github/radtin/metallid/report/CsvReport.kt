package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.report.ReportScenario
import com.github.radtin.metallid.domain.report.ReportStep
import com.github.radtin.metallid.domain.report.ReportSuite

import java.io.File

class CsvReport(private val reportSuite: ReportSuite,
                val properties: MutableMap<String, String>? = null) : MetalLidReport(reportSuite, properties) {

    override fun outputResults() {
        var content = when (isDetailed()) {
            true -> "Test Scenario,Test Step,Status,Class,Method,Input,Output\n"
            false -> "Test Scenario,Test Step,Status,Output\n"
        }
        for (scenario: ReportScenario in reportSuite.scenarios) {
            for (step: ReportStep in scenario.steps) {
                content = when (isDetailed()) {
                    true -> content.plus("${scenario.name},${step.name},${status(step.output)},${step.className},${step.methodName},\"${step.value}\",${results(step.output)}\n")
                    false -> content.plus("${scenario.name},${step.name},${status(step.output)},${results(step.output)}\n")
                }
            }
        }

        val file = File("test-output/${getFilename()}.csv")
        file.createNewFile()
        file.writeText(content)
    }
}