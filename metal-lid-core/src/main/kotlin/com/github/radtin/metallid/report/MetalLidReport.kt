package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.report.ReportOutput
import com.github.radtin.metallid.domain.report.ReportSuite

abstract class MetalLidReport(private val reportSuite: ReportSuite,
                              private val properties: MutableMap<String, String>?) {

    private var propertiesMap = HashMap<String, String>()

    init  {
        propertiesMap["email"] = if (!properties.isNullOrEmpty() && properties.containsKey("email")) { properties["email"]!! } else { "" }
        propertiesMap["filename"] = if (!properties.isNullOrEmpty() && properties.containsKey("filename")) { properties["filename"]!! } else { reportSuite.name.trim().replace(" ", "_") }
        propertiesMap["ftp"] = if (!properties.isNullOrEmpty() && properties.containsKey("ftp")) { properties["ftp"]!! } else { "" }
        propertiesMap["detailed"] = if (!properties.isNullOrEmpty() && properties.containsKey("detailed")) { properties["detailed"]!! } else { "false" }
    }

    fun getFilename(): String {
        return propertiesMap["filename"]!!
    }

    fun getEmail(): String {
        return propertiesMap["email"]!!
    }

    fun getFtp(): String {
        return propertiesMap["ftp"]!!
    }

    fun isDetailed(): Boolean {
        return propertiesMap["detailed"] == "true"
    }

    fun status(reportOutput: ReportOutput): String {
        if (reportOutput.error?.message.isNullOrEmpty()) {
            return "SUCCESS"
        }

        return "FAILURE"
    }

    abstract fun outputResults()

    fun results(reportOutput: ReportOutput): String {
        if (reportOutput.error?.message.isNullOrEmpty()) {
            return reportOutput.value
        }

        return reportOutput.error?.message!!
    }
}