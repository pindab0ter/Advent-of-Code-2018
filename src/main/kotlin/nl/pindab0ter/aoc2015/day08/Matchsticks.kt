package nl.pindab0ter.aoc2015.day08

import nl.pindab0ter.common.getInput

private val hexRegex = """(?!<\\)\\x([a-f0-9]{2})""".toRegex()

fun parse(string: String) = string
    .trim()
    .replace("""\\""", "^")
    .replace(hexRegex) { matchResult ->
        val hexValue = matchResult.groupValues[1].toInt(16)
        hexValue.toChar().toString()
    }
    .replace("""\"""", """"""")
    .replace("^", """\""")
    .replace("""^"|"$""".toRegex(), "")


fun main() {
    val input = getInput(2015, 8)

    val lines = input.lines()
    val preParseLength = lines.sumOf(String::length)

    val parsedLines = input.lines().map(::parse)
    val postParseLength = parsedLines.sumOf(String::length)

    println("Difference between character count pre and post parse: ${preParseLength - postParseLength}")
}