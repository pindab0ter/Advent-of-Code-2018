package nl.pindab0ter.aoc2018.day01

import nl.pindab0ter.common.helpers.assertAllEquals
import org.junit.jupiter.api.Test

internal class ChronalCalibrationKtTest {
    @Test
    fun findFinalFrequency() = assertAllEquals(
        findFinalFrequency(listOf(1, -2, 3, 1)) to 3,
        findFinalFrequency(listOf(1, 1, 1)) to 3,
        findFinalFrequency(listOf(1, 1, -2)) to 0,
        findFinalFrequency(listOf(-1, -2, -3)) to -6,
    )

    @Test
    fun findFirstRepeatedFrequency() = assertAllEquals(
        findFirstRepeatedFrequency(listOf(1, -1)) to 0,
        findFirstRepeatedFrequency(listOf(3, 3, 4, -2, -4)) to 10,
        findFirstRepeatedFrequency(listOf(-6, 3, 8, 5, -6)) to 5,
        findFirstRepeatedFrequency(listOf(7, 7, -2, -7, -4)) to 14,
    )
}