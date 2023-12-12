package nl.pindab0ter.aoc2023.day05

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("2023 Day 05 - If You Give A Seed A Fertilizer")
class IfYouGiveASeedAFertilizerKtTest {
    @ParameterizedTest(name = "{0} → {1}")
    @MethodSource("firstMapReverseProvider")
    @DisplayName("Apply first map in reverse")
    fun `Apply first map in reverse`(actual: UInt, location: UInt) {
        assertEquals(actual.toULong(), applyMapReverse(location.toULong(), almanac.maps.first()))
    }

    @Test
    fun `Apply all maps in reverse`() {
        val almanac = Almanac(input)
        val actual = almanac.maps.reversed().fold(82u.toULong()) { acc, map -> applyMapReverse(acc, map) }
        Assertions.assertEquals(79u.toULong(), actual)
    }

    @Test
    fun `Reverse search through all maps`() {
        val almanac = Almanac(input)
        val result = findFirstSeedRanges(almanac.partTwoSeeds, almanac.maps)
        Assertions.assertEquals(46u.toULong(), result)
    }

    companion object {
        private val input = """
            seeds: 79 14 55 13
    
            seed-to-soil map:
            50 98 2
            52 50 48
    
            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15
    
            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4
    
            water-to-light map:
            88 18 7
            18 25 70
    
            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13
    
            temperature-to-humidity map:
            0 69 1
            1 0 69
    
            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent()
        private val almanac = Almanac(input)

        @JvmStatic
        private fun firstMapReverseProvider(): Stream<Arguments> = Stream.of(
            arguments("0", "0"),
            arguments("1", "1"),
            arguments("48", "48"),
            arguments("49", "49"),
            arguments("50", "52"),
            arguments("51", "53"),
            arguments("96", "98"),
            arguments("97", "99"),
            arguments("98", "50"),
            arguments("99", "51"),
        )
    }
}