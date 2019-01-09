package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.report.ReportScenario
import com.github.radtin.metallid.domain.report.ReportStep
import com.github.radtin.metallid.domain.report.ReportSuite

class ConsoleReport : MetalLidReport(null, null) {

    override fun applyProperties() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun outputResults(report: ReportSuite) {
        println(report.name)
        for (scenario: ReportScenario in report.scenarios) {
            println("\n    ".plus(scenario.name).plus(":"))
            for (step: ReportStep in scenario.steps) {
                println("        ".plus(step.name).plus(":"))
                println("            status: ".plus(status(step.output)))
                println("            output: ".plus(results(step.output)))
            }
        }
    }
}