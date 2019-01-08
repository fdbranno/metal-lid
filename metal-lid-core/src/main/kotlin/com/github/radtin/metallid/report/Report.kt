package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.Output
import com.github.radtin.metallid.domain.Scenario
import com.github.radtin.metallid.domain.Step

interface Report {

    fun applyProperties()

    fun outputResults(scenario: Scenario, step: Step, output: Output)
}