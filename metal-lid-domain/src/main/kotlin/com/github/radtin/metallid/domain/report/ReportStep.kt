package com.github.radtin.metallid.domain.report

data class ReportStep(val name: String,
                      val className: String,
                      val methodName: String,
                      val value: String,
                      val output: ReportOutput)