package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.report.ReportScenario
import com.github.radtin.metallid.domain.report.ReportStep
import com.github.radtin.metallid.domain.report.ReportSuite
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFColor
import org.apache.poi.xssf.usermodel.XSSFFont
import java.io.FileOutputStream
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.awt.Color


class XlsReport : MetalLidReport(null, null) {

    private var workbook = XSSFWorkbook()

    private var red: XSSFColor = XSSFColor(Color.RED)
    private var green = XSSFColor(Color.GREEN)
    private var blue = XSSFColor(Color.BLUE)
    private var white = XSSFColor(Color.WHITE)

    private var boldFont: XSSFFont
    private var whiteFont: XSSFFont
    private var boldWhiteFont: XSSFFont

    private var headerStyle: XSSFCellStyle
    private var columnStyle: XSSFCellStyle
    private var standardStyle: XSSFCellStyle
    private var successStyle: XSSFCellStyle
    private var failureStyle: XSSFCellStyle

    init {
        green.tint = -0.5

        red.tint = -0.5

        boldFont = workbook.createFont()
        boldFont.bold = true

        whiteFont = workbook.createFont()
        whiteFont.setColor(white)

        boldWhiteFont = workbook.createFont()
        boldWhiteFont.bold = true
        boldWhiteFont.setColor(white)

        headerStyle = workbook.createCellStyle()
        headerStyle.setFont(boldWhiteFont)
        headerStyle.setBorderRight(BorderStyle.THIN)
        headerStyle.setBorderLeft(BorderStyle.THIN)
        headerStyle.setBorderBottom(BorderStyle.THIN)
        headerStyle.setFillBackgroundColor(blue)
        headerStyle.setFillForegroundColor(blue)
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND)
        headerStyle.alignment = CellStyle.ALIGN_CENTER

        columnStyle = workbook.createCellStyle()
        columnStyle.setFont(boldFont)
        columnStyle.setBorderRight(BorderStyle.THIN)
        columnStyle.setBorderLeft(BorderStyle.THIN)
        columnStyle.setBorderBottom(BorderStyle.MEDIUM)

        standardStyle = workbook.createCellStyle()
        standardStyle.setBorderRight(BorderStyle.THIN)
        standardStyle.setBorderLeft(BorderStyle.THIN)
        standardStyle.setBorderBottom(BorderStyle.THIN)

        successStyle = workbook.createCellStyle()
        successStyle.setFillBackgroundColor(green)
        successStyle.setFillForegroundColor(green)
        successStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND)
        successStyle.setBorderRight(BorderStyle.THIN)
        successStyle.setBorderLeft(BorderStyle.THIN)
        successStyle.setBorderBottom(BorderStyle.THIN)
        successStyle.setFont(whiteFont)

        failureStyle = workbook.createCellStyle()
        failureStyle.setFillBackgroundColor(red)
        failureStyle.setFillForegroundColor(red)
        failureStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND)
        failureStyle.setBorderRight(BorderStyle.THIN)
        failureStyle.setBorderLeft(BorderStyle.THIN)
        failureStyle.setBorderBottom(BorderStyle.THIN)
        failureStyle.setFont(whiteFont)
    }

    override fun applyProperties() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun outputResults(report: ReportSuite) {
        val sheet = workbook.createSheet(report.name)
        val bookData = mutableListOf(mutableListOf(report.name, "", "", ""), mutableListOf("TestScenario", "TestStep", "Status", "Output"))

        var beginScenarioRow = 2
        val scenarioIndexes = mutableListOf<MutableList<Int>>()

        for (scenario: ReportScenario in report.scenarios) {
            var index = 0
            for (step: ReportStep in scenario.steps) {
                val scenarioCell = if (index == 0) { scenario.name } else { "" }
                val values = mutableListOf(scenarioCell, step.name, status(step.output), results(step.output))
                bookData.add(values)
                index++
            }

            val endScenarioRow = beginScenarioRow + index - 1
            scenarioIndexes.add(mutableListOf(beginScenarioRow, endScenarioRow))
            beginScenarioRow += index
        }

        var rowCount = 0

        bookData.forEach { aBook ->
            val row = sheet.createRow(rowCount++)

            aBook.forEachIndexed { column, field ->
                val cell = row.createCell(column)
                cell.setCellValue(field)
                cell.cellStyle = if (rowCount == 1) { headerStyle } else if(rowCount == 2) { columnStyle } else { getStyle(field) }
            }
        }

        sheet.addMergedRegion(CellRangeAddress(0,0,0,3))

        for (rowSet: MutableList<Int> in scenarioIndexes) {
            sheet.addMergedRegion(CellRangeAddress(rowSet[0], rowSet[1], 0, 0))
        }

        FileOutputStream("test-output/".plus(report.name.trim().replace(" ", "_")).plus(".xlsx")).use { outputStream -> workbook.write(outputStream) }
    }

    private fun getStyle(field: String): XSSFCellStyle {
        if (field == "SUCCESS") { return successStyle }
        else if (field == "FAILURE") { return failureStyle }
        return standardStyle
    }
}