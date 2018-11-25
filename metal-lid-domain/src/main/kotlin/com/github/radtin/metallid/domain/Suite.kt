package com.github.radtin.metallid.domain

import com.github.radtin.metallid.domain.configuration.Configuration

data class Suite(val name: String,
                 val scenarios: List<Scenario>,
                 val configurations: List<Configuration>?)