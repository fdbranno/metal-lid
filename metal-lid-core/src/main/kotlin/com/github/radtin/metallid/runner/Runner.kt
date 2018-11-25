package com.github.radtin.metallid.runner

import com.github.radtin.metallid.domain.Input
import com.github.radtin.metallid.domain.Output
import com.github.radtin.metallid.domain.Step

var storedTokens: MutableMap<String, String> = mutableMapOf()

abstract class Runner {
    abstract fun run()
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
fun setOutput(step: Step, output: Output) {
    if (step.output != null) storedTokens["\${${step.output!!}}"] = output.value
}