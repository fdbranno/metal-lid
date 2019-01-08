package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.Output
import com.github.radtin.metallid.domain.Scenario
import com.github.radtin.metallid.domain.Step
import java.io.File
import java.io.FileOutputStream
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



class XlsReport(private val filename: String,
                private val properties: Map<String, String>) : Report {

    private lateinit var workbook: XSSFWorkbook
    private lateinit var sheet: XSSFSheet

    init {
        workbook = XSSFWorkbook()
        sheet = workbook.createSheet(filename)
        writeToFile(arrayOf(arrayOf("TestScenario", "TestStep", "Status", "StackTrace")))
    }

    override fun applyProperties() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun outputResults(scenario: Scenario, step: Step, output: Output) {
        val status: String = if (output.error?.message.isNullOrEmpty()) {
            "SUCCESS"
        } else {
            output.error?.message.toString()
        }

        val stackTrace: String = if (output.error?.message.isNullOrEmpty()) {
            ""
        } else {
            output.error?.stackTrace.toString()
        }

        writeToFile(arrayOf(arrayOf(scenario.name, step.name, status, stackTrace)))
    }

    private fun writeToFile(bookData: Array<Array<String>>) {
        var rowCount = 0

        for (aBook in bookData) {
            val row = sheet.createRow(++rowCount)

            var columnCount = 0

            for (field in aBook) {
                val cell = row.createCell(++columnCount)
                cell.setCellValue(field)
            }
        }

        FileOutputStream(filename.plus(".xls")).use { outputStream -> workbook.write(outputStream) }
    }
}