package com.github.radtin.metallid.domain.report

class ReportOutput {

    val value: String
    val error: Exception?

    constructor(value: String, error: Exception? = null) {
        this.value = value
        this.error = error
    }
}