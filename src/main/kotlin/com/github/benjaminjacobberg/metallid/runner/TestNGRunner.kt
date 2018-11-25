package com.github.benjaminjacobberg.metallid.runner

import com.github.benjaminjacobberg.metallid.datasource.DataSource
import com.github.benjaminjacobberg.metallid.datasource.data.TestCase
import com.github.benjaminjacobberg.metallid.datasource.data.TestStep
import com.github.benjaminjacobberg.metallid.datasource.data.TestSuite
import org.springframework.stereotype.Component
import org.testng.TestNG

@Component
class TestNGRunner(private val dataSource: DataSource) : Runner {

    override fun run() {
        val testNg = TestNG()

        val testSuite: TestSuite = dataSource.getData()
        val testCases: List<TestCase> = testSuite.testCases
        for (testCase: TestCase in testCases) {
            val testSteps: List<TestStep> = testCase.testSteps
            for (testStep: TestStep in testSteps) {
                println(testStep)
//                testNg.setTestNames(testSteps.stream().for)
            }
        }
    }

    override fun runTestSuite(testSuiteName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun runTestCase(testCaseName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun runTestStep(testStepName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}