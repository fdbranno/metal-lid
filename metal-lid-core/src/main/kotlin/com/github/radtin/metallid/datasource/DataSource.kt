package com.github.radtin.metallid.datasource

import com.github.radtin.metallid.domain.Suite

interface DataSource {
    fun getData(): Suite
}