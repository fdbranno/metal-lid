package com.github.radtin.metallid.domain.report

data class ReportSuite(val name: String,
                       val scenarios: List<ReportScenario>)