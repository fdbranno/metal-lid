package com.github.benjaminjacobberg.metallid.runner

interface Runner {
    fun run()
    fun runTestSuite(testSuiteName: String)
    fun runTestCase(testCaseName: String)
    fun runTestStep(testStepName: String)
}