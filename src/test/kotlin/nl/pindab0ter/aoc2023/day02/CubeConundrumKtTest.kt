package nl.pindab0ter.aoc2023.day02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class CubeConundrumKtTest {

    //////////////
    // Part One //
    //////////////

    @Test
    fun `Game 1 - Part One`() {
        val game = parseLine("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        val result = enoughCubesAreAvailable(game)
        Assertions.assertEquals(result, true)
    }

    @Test
    fun `Game 2 - Part One`() {
        val game = parseLine("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")
        val result = enoughCubesAreAvailable(game)
        Assertions.assertEquals(result, true)
    }

    @Test
    fun `Game 3 - Part One`() {
        val game = parseLine("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")
        val result = enoughCubesAreAvailable(game)
        Assertions.assertEquals(result, false)
    }

    @Test
    fun `Game 4 - Part One`() {
        val game = parseLine("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red")
        val result = enoughCubesAreAvailable(game)
        Assertions.assertEquals(result, false)
    }

    @Test
    fun `Game 5 - Part One`() {
        val game = parseLine("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
        val result = enoughCubesAreAvailable(game)
        Assertions.assertEquals(result, true)
    }

    //////////////
    // Part Two //
    //////////////

    @Test
    fun `Game 1 - Part Two`() {
        val game = parseLine("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        val result = powerOfMinimalRequiredCubes(game)
        Assertions.assertEquals(result, 48)
    }

    @Test
    fun `Game 2 - Part Two`() {
        val game = parseLine("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")
        val result = powerOfMinimalRequiredCubes(game)
        Assertions.assertEquals(result, 12)
    }

    @Test
    fun `Game 3 - Part Two`() {
        val game = parseLine("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")
        val result = powerOfMinimalRequiredCubes(game)
        Assertions.assertEquals(result, 1560)
    }

    @Test
    fun `Game 4 - Part Two`() {
        val game = parseLine("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red")
        val result = powerOfMinimalRequiredCubes(game)
        Assertions.assertEquals(result, 630)
    }

    @Test
    fun `Game 5 - Part Two`() {
        val game = parseLine("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
        val result = powerOfMinimalRequiredCubes(game)
        Assertions.assertEquals(result, 36)
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