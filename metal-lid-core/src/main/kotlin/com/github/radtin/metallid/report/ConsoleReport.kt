package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.Output
import com.github.radtin.metallid.domain.Scenario
import com.github.radtin.metallid.domain.Step

class ConsoleReport(private val properties: Map<String, String>) : Report {

    override fun applyProperties() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun outputResults(scenario: Scenario, step: Step, output: Output) {
        val content = if (output.error == null) {
            "SUCCESS"
        } else {
            output.error?.message.plus(" :\t").plus(output.error?.stackTrace)
        }
        println(scenario.name.plus(" :\t").plus(step.name).plus(" :\t").plus(content))
    }
}