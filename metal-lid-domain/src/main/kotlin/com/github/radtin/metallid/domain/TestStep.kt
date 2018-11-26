package com.github.radtin.metallid.domain

data class TestStep(val name: String,
                    val className: String,
                    val methodName: String,
                    val value: String = "",
                    val output: String = "",
                    val config: String = "")