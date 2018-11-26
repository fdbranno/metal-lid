package com.github.radtin.metallid.domain

data class TestSuiteConfiguration(var name: String,
                                  var testConfigurations: List<TestConfiguration>)