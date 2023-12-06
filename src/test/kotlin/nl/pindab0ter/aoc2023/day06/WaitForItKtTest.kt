package nl.pindab0ter.aoc2023.day06

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WaitForItKtTest {

    @Test
    fun `First race`() {
        val game = Game.partOne(input).first()

        mapOf(
            game.play(0) to 0L,
            game.play(1) to 6L,
            game.play(2) to 10L,
            game.play(3) to 12L,
            game.play(4) to 12L,
            game.play(5) to 10L,
            game.play(6) to 6L,
            game.play(7) to 0L,
        ).forEach { (actual, expected) ->
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Calculate ways to win`() {
        val games = Game.partOne(input)
        mapOf(
            games[0].waysToWin() to 4,
            games[1].waysToWin() to 8,
            games[2].waysToWin() to 9,
        ).forEach { (actual, expected) ->
            assertEquals(actual, expected)
        }
    }

    companion object {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()
    }

}