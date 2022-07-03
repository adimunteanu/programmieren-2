package util

import com.univocity.parsers.csv.{CsvParser, CsvParserSettings}

import scala.io.Source

object Data {

	/**
	 * Imports a dataset of HTTP messages from the given file path.
	 *
	 * @param fileName Path to the CSV file containing the dataset.
	 * @return a list where each entry corresponds to one line of the CSV file.
	 *         Each entry is itself a list where each entry corresponds to one cell in that line.
	 */
	def readMessages(fileName: String): List[List[String]] = {
		val parserSettings = new CsvParserSettings()
		parserSettings.getFormat.setDelimiter("|")
		parserSettings.setMaxCharsPerColumn(32768)

		val parser: CsvParser = new CsvParser(parserSettings)

		Source
			.fromResource(fileName)
			.getLines()
			.toList
			.drop(1)
			.map(line => parser.parseLine(line))
			.map(line => line.toList)
			.filterNot(line => line == null)
	}
}
