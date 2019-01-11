package com.github.radtin.metallid.action

import com.wanasit.chrono.Chrono
import com.wanasit.chrono.ChronoOption
import com.wanasit.chrono.ParsedResult

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import com.github.radtin.chronojavatest.DateConverter.dateToLocalDateTime
import com.github.radtin.chronojavatest.DateConverter.localDateTimeToDate

class DateParser {

    private var chrono: Chrono

    constructor() {
        chrono = Chrono(ChronoOption.casualOptions())
    }

    constructor(option: ChronoOption) {    //options: casualOptions (recommended), standardOptions
        chrono = Chrono(option)
    }

    fun parseDate(expr: String): LocalDateTime {
        return dateToLocalDateTime(chrono.parseDate(expr))
    }

    fun parseDate(expr: String, ref: LocalDateTime): LocalDateTime {
        return dateToLocalDateTime(chrono.parseDate(expr, localDateTimeToDate(ref)))
    }

    fun parseDate(expr: String, format: String): String {
        return formatDate(parseDate(expr), format)
    }

    fun parseDate(expr: String, ref: LocalDateTime, format: String): String {
        return formatDate(parseDate(expr, ref), format)
    }

    fun parse(expr: String): List<ParsedResult> {
        return chrono.parse(expr)
    }

    fun parse(expr: String, ref: LocalDateTime): List<ParsedResult> {
        return chrono.parse(expr, localDateTimeToDate(ref))
    }

    fun formatDate(date: LocalDateTime?, format: String): String {
        val dtf = DateTimeFormatter.ofPattern(format)
        return date!!.format(dtf)
    }
}
