package com.github.radtin.metallid.domain.configuration

data class Database(val name: String,
                    val className: String,
                    val url: String,
                    val username: String?,
                    val password: String?)