package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.report.ReportScenario
import com.github.radtin.metallid.domain.report.ReportStep
import com.github.radtin.metallid.domain.report.ReportSuite

import org.slf4j.LoggerFactory

import java.lang.StringBuilder

class ConsoleReport(private val reportSuite: ReportSuite) : MetalLidReport(reportSuite, null) {
    private val log = LoggerFactory.getLogger(this::class.qualifiedName)!!

    override fun outputResults() {
        val report = StringBuilder()
        report.append("Test execution results... \n========== ${reportSuite.name} ==========")
        for (scenario: ReportScenario in reportSuite.scenarios) {
            report.append("\n${scenario.name}: \n")
            for (step: ReportStep in scenario.steps) {
                report.append("  ${step.name}: \n")
                report.append("    Status: ${status(step.output)} \n")
                report.append("    Output: ${results(step.output)} \n")
            }
        }
        log.info("{}", report)
    }
}