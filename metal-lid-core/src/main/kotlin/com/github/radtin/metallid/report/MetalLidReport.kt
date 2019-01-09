package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.Output
import com.github.radtin.metallid.domain.report.ReportSuite

abstract class MetalLidReport(filename: String?, properties: Map<String, String>?) {

    abstract fun applyProperties()

    abstract fun outputResults(report: ReportSuite)

    fun status(output: Output): String {
        if (output.error?.message.isNullOrEmpty()) {
            return "SUCCESS"
        }

        return "FAILURE"
    }

    fun results(output: Output): String {
        if (output.error?.message.isNullOrEmpty()) {
            return output.value
        }

        return output.error?.message!!
    }
}