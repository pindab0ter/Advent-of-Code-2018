package nl.pindab0ter.aoc2023.day02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CubeConundrumKtTest {

    //////////////
    // Part One //
    //////////////

    @Test
    fun `Determine if enough cubes are available`() = arrayOf(
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green" to true,
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue" to true,
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red" to false,
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red" to false,
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green" to true,
    ).map { (input, actual) ->
        enoughCubesAreAvailable(parseLine(input)) to actual
    }.forEach { (expected, actual) ->
        assertEquals(actual, expected)
    }

    //////////////
    // Part Two //
    //////////////

    @Test
    fun `Calculate the power of the minimal amount of required cubes`() = arrayOf(
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green" to 48,
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue" to 12,
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red" to 1560,
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red" to 630,
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green" to 36,
    ).map { (input, actual) ->
        powerOfMinimalRequiredCubes(parseLine(input)) to actual
    }.forEach { (expected, actual) ->
        assertEquals(actual, expected)
    }

    @Test
    fun `Total of powers`() {
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green 
       """.trimIndent()
        val games = input.lines().map(::parseLine)
        val result = games.sumOf(::powerOfMinimalRequiredCubes)
        Assertions.assertEquals(result, 2286)
    }

}