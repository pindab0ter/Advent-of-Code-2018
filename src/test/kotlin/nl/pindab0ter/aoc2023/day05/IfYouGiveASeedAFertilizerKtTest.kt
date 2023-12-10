package nl.pindab0ter.aoc2023.day05

import nl.pindab0ter.common.assertAllEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2023 Day 05 - If You Give A Seed A Fertilizer")
class IfYouGiveASeedAFertilizerKtTest {
    @Test
    fun `Apply first map in reverse`() {
        val almanac = Almanac(input)
        assertAllEquals(
            applyMapReverse(0u.toULong(), almanac.maps.first()) to 0u.toULong(),
            applyMapReverse(1u.toULong(), almanac.maps.first()) to 1u.toULong(),
            applyMapReverse(48u.toULong(), almanac.maps.first()) to 48u.toULong(),
            applyMapReverse(49u.toULong(), almanac.maps.first()) to 49u.toULong(),
            applyMapReverse(52u.toULong(), almanac.maps.first()) to 50u.toULong(),
            applyMapReverse(53u.toULong(), almanac.maps.first()) to 51u.toULong(),
            applyMapReverse(98u.toULong(), almanac.maps.first()) to 96u.toULong(),
            applyMapReverse(99u.toULong(), almanac.maps.first()) to 97u.toULong(),
            applyMapReverse(50u.toULong(), almanac.maps.first()) to 98u.toULong(),
            applyMapReverse(51u.toULong(), almanac.maps.first()) to 99u.toULong(),
        )
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