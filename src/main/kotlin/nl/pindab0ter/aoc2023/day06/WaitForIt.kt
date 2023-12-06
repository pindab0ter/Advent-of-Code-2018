package nl.pindab0ter.aoc2023.day06

import nl.pindab0ter.common.productOf

fun main() {
    val input = ClassLoader.getSystemResource("2023/day06/input").readText()

    val games = Game(input)

    println(games.productOf { game -> game.waysToWin() })
}

data class Game(
    val time: Int,
    val recordDistance: Int,
) {
    @Suppress("UnnecessaryVariable")
    fun play(chargeTime: Int): Int {
        val speed = chargeTime
        val distance = speed * (time - chargeTime)
        return distance
    }

    fun waysToWin(): Int = (1..time).count { seconds ->
        play(seconds) > recordDistance
    }

    companion object {
        operator fun invoke(input: String): List<Game> = input.lines()
            .map { line -> line.split(""":\s+""".toRegex()).last() }
            .map { line -> line.split("""\s+""".toRegex()) }
            .map { numbers -> numbers.map(String::toInt) }
            .let { (time, distance) -> time.zip(distance) }
            .map { (time, distance) -> Game(time, distance) }
    }
}
