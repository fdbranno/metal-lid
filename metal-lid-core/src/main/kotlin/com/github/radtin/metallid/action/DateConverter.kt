package com.github.radtin.chronojavatest

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

internal object DateConverter {
    fun dateToLocalDateTime(date: Date): LocalDateTime {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
    }

    fun localDateTimeToDate(date: LocalDateTime): Date {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant())
    }
}
