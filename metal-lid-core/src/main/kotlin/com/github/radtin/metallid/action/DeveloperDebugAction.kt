package com.github.radtin.metallid.action

import com.github.radtin.metallid.domain.Input
import com.github.radtin.metallid.domain.Output
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.ZoneOffset

class DeveloperDebugAction {
    private val log = LoggerFactory.getLogger(this::class.qualifiedName)!!

    fun output(input: Input): Output {
        val value = "${input.value} ${LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()}"
        log.info(value)
        return Output(value)
    }

}