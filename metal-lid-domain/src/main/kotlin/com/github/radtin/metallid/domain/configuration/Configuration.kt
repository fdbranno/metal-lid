package com.github.radtin.metallid.domain.configuration

data class Configuration(val database: List<Database>?,
                         val messageBroker: List<MessageBroker>?,
                         val kafka: List<Any>?,
                         val rest: List<Any>?,
                         val soap: List<Any>?,
                         val webBrowser: List<Any>?)