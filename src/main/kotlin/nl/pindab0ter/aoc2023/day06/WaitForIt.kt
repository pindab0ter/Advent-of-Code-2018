package nl.pindab0ter.aoc2023.day06

import nl.pindab0ter.common.productOf

fun main() {
    val input = ClassLoader.getSystemResource("2023/day06/input").readText()

    val waysToWinPartOne = Game.partOne(input).productOf { game -> game.waysToWin() }
    val waysToWinPartTwo = Game.partTwo(input).waysToWin()

    println("Ways to win (part one): $waysToWinPartOne")
    println("Ways to win (part two): $waysToWinPartTwo")
}

data class Game(
    val time: Long,
    val recordDistance: Long,
) {
    @Suppress("UnnecessaryVariable")
    fun play(chargeTime: Long): Long {
        val speed = chargeTime
        val distance = speed * (time - chargeTime)
        return distance
    }

    fun waysToWin(): Int = (1..time).count { seconds ->
        play(seconds) > recordDistance
    }

    companion object {
        fun partOne(input: String): List<Game> = input.lines()
            .map { line -> line.split(""":\s+""".toRegex()).last() }
            .map { line -> line.split("""\s+""".toRegex()) }
            .map { numbers -> numbers.map(String::toLong) }
            .let { (time, distance) -> time.zip(distance) }
            .map { (time, distance) -> Game(time, distance) }

        fun partTwo(input: String): Game = input.lines()
            .map { line -> line.split(""":\s+""".toRegex()).last() }
            .map { line -> line.split("""\s+""".toRegex()).joinToString("") }
            .map(String::toLong)
            .let { (time, distance) -> Game(time, distance) }
    }
}
