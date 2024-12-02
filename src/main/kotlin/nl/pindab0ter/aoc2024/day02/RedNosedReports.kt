package nl.pindab0ter.aoc2024.day02

import arrow.core.tail
import nl.pindab0ter.aoc2024.day02.Status.*
import nl.pindab0ter.common.getInput

fun main() {
    val input = getInput(2024, 2).parse()

    val safeReportCount = input.map(Report::status).count { it != UNSAFE }

    println("Amount of safe reports: $safeReportCount")
}

typealias Level = Int
typealias Report = List<Level>

enum class Status {
    INCREASING, DECREASING, UNSAFE
}

fun Pair<Level, Level>.status(): Status = when (second) {
    in first + 1..first + 3 -> INCREASING
    in first - 3..first - 1 -> DECREASING
    else -> UNSAFE
}

fun Report.status(): Status {
    val (firstLevel, secondLevel) = this
    val firstPairStatus = Pair(firstLevel, secondLevel).status()

    if (firstPairStatus == UNSAFE) return UNSAFE

    return tail().windowed(2).fold(firstPairStatus) { status, (a, b) ->
        if (Pair(a, b).status() != status) return UNSAFE
        status
    }
}

fun String.parse(): List<Report> = lines().map { line ->
    Regex("""(\d+(?=\s+)?)""").findAll(line).map { match ->
        match.value.toInt()
    }.toList()
}
