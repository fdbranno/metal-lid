package com.github.radtin.metallid.datasource

import com.github.radtin.metallid.domain.TestSuite
import com.github.radtin.metallid.domain.TestSuiteConfiguration

interface DataSource {
    fun getData(): TestSuite
    fun getConfigs(): TestSuiteConfiguration
}