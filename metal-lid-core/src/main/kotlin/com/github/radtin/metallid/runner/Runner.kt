package com.github.radtin.metallid.runner

import com.github.radtin.metallid.datasource.DataSource
import com.github.radtin.metallid.domain.*
import com.github.radtin.metallid.domain.configuration.Configuration
import com.github.radtin.metallid.domain.configuration.Database

var storedTokens: MutableMap<String, String> = mutableMapOf()

abstract class Runner(private val dataSource: DataSource) {
    abstract fun run()

    protected fun configuration() {
        if (dataSource.getData().configurations != null) {
            for (configuration: Configuration in dataSource.getData().configurations!!) {
                if (configuration.database == null) {
                    continue
                }
                for (database: Database in configuration.database!!) {
                    database.className
                }
            }
        }
    }
}

fun runTestSteps(scenario: Scenario) {
    val steps = scenario.steps
    for (step: Step in steps) {
        val className = step.className
        val method = step.methodName
        val output: Output = invokeMethod(className, method, Input(setValue(step), ""))
        saveOutput(step, output)
    }
}

/**
 * Run an external method through reflection.  The class and method will be specified from the datasource.
 */
fun invokeMethod(className: String, method: String, input: Input): Output {
    val sys = Class.forName(className)
    val met = sys.getMethod(method, Input::class.javaObjectType)
    return met.invoke(sys.newInstance(), input) as Output
}

/**
 * If a token is given, fetch the value from the storedTokens MutableMap otherwise, continue with the passed value.
 */
fun setValue(step: Step): String {
    return if (step.value.contains(Regex("\\$\\{(.*)\\}"))) storedTokens.getValue(step.value) else step.value
}

/**
 * If an output value is provided, store the value and token in the storedTokens MutableMap.
 */
fun saveOutput(step: Step, output: Output) {
    if (step.output != null) storedTokens["\${${step.output!!}}"] = output.value
}