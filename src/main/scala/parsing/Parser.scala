package parsing

import models._

object Parser extends Encoding {

  def parse(parserInput: ParserInput): ParsedCSV = {
    parsedCsv(extractHeaders(parserInput))
  }

  private val parsedCsv: ParserInput => ParsedCSV = parserInput => {
    parserInput.in match {
      case FileType(input)     => ???
      case IteratorType(input) => ???
      case IterableType(input) => ???
      case ReaderType(input)   => ???
      case SeqType(input) =>
        val parsed: Seq[(String, String)] = input.map(parsingLogic)

        ParsedCSV(
          headers = parserInput.headers,
          parsedLines = parsed.map(_._1).iterator,
          droppedLines = parsed.map(_._2).toList
        )

      case SourceType(input) => ???
      case StreamType(input) => ???
    }
  }

  private val extractHeaders: ParserInput => ParserInput = { parserInput =>
    {
      if (parserInput.headers.isEmpty) {
        parserInput.in match {
          case FileType(input)     => ???
          case IteratorType(input) => ???
          case IterableType(input) => ???
          case ReaderType(input)   => ???
          case SeqType(input) =>
            val headers: List[String] = input.take(1).toList
            val lines: Seq[String] = input.drop(1)
            ParserInput(
              in = SeqType(lines),
              csvDefinition = parserInput.csvDefinition,
              headers = headers
            )
          case SourceType(input) => ???
          case StreamType(input) => ???
        }
      } else {
        parserInput
      }
    }
  }

  private val parsingLogic: String => (String, String) = { ??? }

}
