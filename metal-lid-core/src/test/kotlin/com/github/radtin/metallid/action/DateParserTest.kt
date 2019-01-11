package com.github.radtin.metallid.action

import com.wanasit.chrono.ChronoOption
import org.junit.Test

import java.io.File
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.LinkedList
import java.util.Scanner

import com.github.radtin.metallid.action.TimeZone.changeTimeZone
import java.lang.IllegalStateException

class DateParserTest {

    private var parser: DateParser = DateParser()

    private var refDate: LocalDateTime = LocalDateTime.now().minusYears(1)

    private var expressions = fileToList("src/test/resources/date_expressions.txt")
    private var nattyExpressions = fileToList("src/test/resources/natty_date_expressions.txt")
    private var formats = fileToList("src/test/resources/date_formats.txt")

    private fun fileToList(filepath: String): LinkedList<String> {
        val list = LinkedList<String>()
        try {
            val file = File(filepath)
            val s = Scanner(file)
            while (s.hasNextLine()) {
                list.add(s.nextLine())
            }
            s.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    @Test
    fun parseDateTest() {
        println("\n----------------------------")
        println("\nParse Date\n")

        for (expr in expressions) {
            println("$expr => ")
            try {
                print(parser.parseDate(expr))
            } catch (e: IllegalStateException) {
                print(null)
            }
        }
    }

    @Test
    fun parseFormattedDateTest() {
        println("\n----------------------------")
        println("\nParse Formatted Date\n")

        val format = "yyyy-MM-dd hh:mm:ss"
        println("Format: $format\n")

        for (expr in expressions) {
            val value = try {
                parser.parseDate(expr, format)
            } catch (e: IllegalStateException) {
                null
            }
            println("$expr => $value")
        }
    }

    @Test
    fun parseRefDateTest() {
        println("\n----------------------------")
        println("\nParse Date With Reference\n")

        println("Reference Date: $refDate\n")

        for (expr in expressions) {
            val value = try {
                parser.parseDate(expr, refDate)
            } catch (e: IllegalStateException) {
                null
            }
            println("$expr => $value")
        }
    }

    @Test
    fun parseFormattedRefDateTest() {
        println("\n----------------------------")
        println("\nParse Formatted Date With Reference\n")

        println("Reference Date: $refDate\n")

        val format = "yyyy-MM-dd hh:mm:ss"
        println("Format: $format\n")

        for (expr in expressions) {
            val value = try {
                parser.parseDate(expr, refDate, format)
            } catch (e: IllegalStateException) {
                null
            }
            println("$expr => $value")
        }
    }

    @Test
    fun parseTest() {
        println("\n----------------------------")
        println("\nParse\n")

        for (expr in expressions) {
            val value = try {
                parser.parse(expr)
            } catch (e: IllegalStateException) {
                null
            }
            println("$expr => $value")
        }
    }

    @Test
    fun parseRefTest() {
        println("\n----------------------------")
        println("\nParse With Reference\n")

        println("Reference Date: $refDate\n")

        for (expr in expressions) {
            val value = try {
                parser.parse(expr, refDate)
            } catch (e: IllegalStateException) {
                null
            }
            println("$expr => $value")
        }
    }

    @Test
    fun formatterTest() {
        println("\n----------------------------")
        println("\nFormat Date Test\n")

        val date = parser.parseDate(expressions[0])

        for (format in formats) {
            println("Date: $date")
            println("Format: $format")
            println("Formatted Date: " + parser.formatDate(date, format) + "\n")
        }
    }

    @Test
    fun changeTimezoneTest() {
        println("\n----------------------------")
        println("\nChange Time Zone Test\n")

        val date = parser.parseDate(expressions[0])
        val timezone = "GMT"

        println("Date at " + ZoneId.systemDefault() + ": " + date)
        println("Date at " + ZoneId.of(timezone) + ": " + changeTimeZone(date, timezone) + "\n")
    }

    @Test
    fun standardOptionsTest() {
        println("\n----------------------------")
        println("\nParse Date With Standard Options Test\n")

        val parser = DateParser(ChronoOption.standardOptions())

        for (expr in expressions) {
            val value = try {
                parser.parseDate(expr)
            } catch (e: IllegalStateException) {
                null
            }
            println("$expr => $value")
        }
    }

    @Test
    fun nattyExpressionsCasualTest() {
        println("\n----------------------------")
        println("\nNatty Expressions With Casual Options Test\n")

        val parser = DateParser()

        for (expr in nattyExpressions) {
            val value = try {
                parser.parseDate(expr)
            } catch (e: IllegalStateException) {
                null
            }
            println("$expr => $value")
        }
    }

    @Test
    fun nattyExpressionsStandardTest() {
        println("\n----------------------------")
        println("\nNatty Expressions With Standard Options Test\n")

        val parser = DateParser(ChronoOption.standardOptions())

        for (expr in nattyExpressions) {
            val value = try {
                parser.parseDate(expr)
            } catch (e: IllegalStateException) {
                null
            }
            println("$expr => $value")
        }
    }
}
