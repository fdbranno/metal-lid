package com.github.radtin.metallid.report

import com.github.radtin.metallid.domain.report.ReportScenario
import com.github.radtin.metallid.domain.report.ReportStep
import com.github.radtin.metallid.domain.report.ReportSuite

import java.io.File

class HtmlReport(private val reportSuite: ReportSuite,
                 val properties: MutableMap<String, String>? = null) : MetalLidReport(reportSuite, properties) {

    override fun outputResults() {
        var template = """<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${"$"}title</title>
    <style type="text/css">
      .error { color: red; }
      .success { color: green; }

      table {
        border: 1px solid #1a1a1a;
        border-spacing: 0;
        border-collapse: collapse;
      }

      th {
        text-align: left;
      }

      tr:nth-child(even) {
        background: rgba(0,0,0,0.05);
      }

      tr:hover {
        background: rgba(0,0,0,0.10);
      }

      td, th {
        border: 1px solid #3a3a3a;
        margin: 0; padding: 0.5em;
      }
    </style>
  </head>
  <body>
${"$"}body
</body>
</html>"""

        val detail = when (isDetailed()) {
            true -> "\n    <th>Class</th> \n    <th>Method</th> \n    <th>Input</th> \n"
            false -> ""
        }

        var body = """<table class="tg">
  <tr>
    <th>Test Scenario</th>
    <th>Test Step</th>
    <th>Status</th>$detail
    <th>Output</th>
  </tr>
${"$"}content"""

        var content = ""
        for (scenario: ReportScenario in reportSuite.scenarios) {
            for (step: ReportStep in scenario.steps) {
                var row = "  <tr>\n"
                row = row.plus("    <td>${scenario.name}</td>\n")
                row = row.plus("    <td>${step.name}</td>\n")
                val classValue = if (status(step.output) == "SUCCESS") { "success" } else { "error" }
                row = row.plus("    <td class=$classValue>${status(step.output)}</td>\n")
                val detailRow = when (isDetailed()) {
                    true -> "    <td>${step.className}</td> \n<td>${step.methodName}</td> \n<td>\"${step.value}\"</td> \n"
                    false -> ""
                }
                row = row.plus(detailRow)
                row = row.plus("    <td>${results(step.output)}</td>\n")
                row = row.plus("  </tr>\n")
                content = content.plus(row)
            }
        }

        body = body.replace("\$content", content).plus("</table>")

        template = template.replace("\$title", reportSuite.name)
        template = template.replace("\$body", body)

        val file = File("test-output/${getFilename()}.html")
        file.createNewFile()
        file.writeText(template)
    }
}