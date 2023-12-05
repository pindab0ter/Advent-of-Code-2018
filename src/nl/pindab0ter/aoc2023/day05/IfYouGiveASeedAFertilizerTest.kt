package nl.pindab0ter.aoc2023.day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class IfYouGiveASeedAFertilizerTest {
    @Test
    fun `Apply first map in reverse`() {
        val almanac = Almanac(input)
        mapOf(
            0u.toULong() to applyMapReverse(0u.toULong(), almanac.maps.first()),
            1u.toULong() to applyMapReverse(1u.toULong(), almanac.maps.first()),
            48u.toULong() to applyMapReverse(48u.toULong(), almanac.maps.first()),
            49u.toULong() to applyMapReverse(49u.toULong(), almanac.maps.first()),
            50u.toULong() to applyMapReverse(52u.toULong(), almanac.maps.first()),
            51u.toULong() to applyMapReverse(53u.toULong(), almanac.maps.first()),
            96u.toULong() to applyMapReverse(98u.toULong(), almanac.maps.first()),
            97u.toULong() to applyMapReverse(99u.toULong(), almanac.maps.first()),
            98u.toULong() to applyMapReverse(50u.toULong(), almanac.maps.first()),
            99u.toULong() to applyMapReverse(51u.toULong(), almanac.maps.first()),
        ).forEach { (expected, actual) ->
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Apply all maps in reverse`() {
        val almanac = Almanac(input)
        val actual = almanac.maps.reversed().fold(82u.toULong()) { acc, map -> applyMapReverse(acc, map) }
        assertEquals(79u.toULong(), actual)
    }

    @Test
    fun `Reverse search through all maps`() {
        val almanac = Almanac(input)
        val result = findFirstSeed(almanac.partTwoSeeds, almanac.maps)
        assertEquals(46u.toULong(), result)
    }

    companion object {
        val input = """
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
    }
}
