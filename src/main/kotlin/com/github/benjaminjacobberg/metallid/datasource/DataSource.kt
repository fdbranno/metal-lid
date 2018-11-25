package com.github.benjaminjacobberg.metallid.datasource

import com.github.benjaminjacobberg.metallid.datasource.data.TestSuite

interface DataSource {
    fun getData(): TestSuite
}