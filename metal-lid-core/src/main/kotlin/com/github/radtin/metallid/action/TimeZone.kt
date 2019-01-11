package com.github.radtin.metallid.action

import java.time.LocalDateTime
import java.time.ZoneId

object TimeZone {
    fun changeTimeZone(date: LocalDateTime, timezone: String): LocalDateTime {
        return date.atZone(ZoneId.systemDefault())?.withZoneSameInstant(ZoneId.of(timezone))!!.toLocalDateTime()
    }
}
