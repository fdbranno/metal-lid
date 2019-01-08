package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.Output
import com.github.radtin.metallid.domain.Scenario
import com.github.radtin.metallid.domain.Step
import java.io.File

class CsvReport(private val filename: String,
                private val properties: Map<String, String>) : Report {

    private var file: File

    init {
        file = File(filename.plus(".csv"))
        file.bufferedWriter().use { out -> out.write("TestScenario,TestStep,Status,StackTrace\n") }
    }

    override fun applyProperties() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun outputResults(scenario: Scenario, step: Step, output: Output) {
        val content = if (output.error?.message.isNullOrEmpty()) {
            "SUCCESS"
        } else {
            output.error?.message.plus(",").plus(output.error?.stackTrace)
        }
        file.bufferedWriter().use { out -> out.write(scenario.name.plus(",").plus(step.name).plus(",").plus(content))}
    }
}