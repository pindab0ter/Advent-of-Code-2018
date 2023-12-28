package nl.pindab0ter.aoc2018.day03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2018 Day 03 - No Matter How You Slice It")
class NoMatterHowYouSliceItKtTest {

    private val input = listOf(
        "#1 @ 1,3: 4x4",
        "#2 @ 3,1: 4x4",
        "#3 @ 5,5: 2x2"
    ).map(Claim.Factory::fromString)

    @Test
    fun contestedClaims() {
        val actual = contestedAreas(input)
        Assertions.assertEquals(4, actual)
    }

    @Test
    fun findNonOverlappingClaim() {
        val actual = findNonOverlappingClaim(input)
        Assertions.assertEquals(Claim(3, Claim.Area(5, 5, 2, 2)), actual)
    }
}