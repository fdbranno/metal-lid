package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.report.ReportOutput
import com.github.radtin.metallid.domain.report.ReportSuite

abstract class MetalLidReport(private val reportSuite: ReportSuite,
                              private val properties: MutableMap<String, String>?) {

    private var propertiesMap = HashMap<String, String>()

    fun applyProperties(): MutableMap<String, String> {
        propertiesMap["email"] = if (!properties.isNullOrEmpty() && properties.containsKey("email")) { properties["email"]!! } else { "" }
        propertiesMap["filename"] = if (!properties.isNullOrEmpty() && properties.containsKey("filename")) { properties["filename"]!! } else { reportSuite.name.trim().replace(" ", "_") }
        propertiesMap["ftp"] = if (!properties.isNullOrEmpty() && properties.containsKey("ftp")) { properties["ftp"]!! } else { "" }
        return propertiesMap
    }

    abstract fun outputResults()

    fun status(reportOutput: ReportOutput): String {
        if (reportOutput.error?.message.isNullOrEmpty()) {
            return "SUCCESS"
        }

        return "FAILURE"
    }

    fun results(reportOutput: ReportOutput): String {
        if (reportOutput.error?.message.isNullOrEmpty()) {
            return reportOutput.value
        }

        return reportOutput.error?.message!!
    }
}