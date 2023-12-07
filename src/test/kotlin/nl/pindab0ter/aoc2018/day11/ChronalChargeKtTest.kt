package nl.pindab0ter.aoc2018.day11

import nl.pindab0ter.common.helpers.assertAllEquals
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("2018 Day 11 - Chronal Charge")
class ChronalChargeKtTest {

    @Test
    fun `Grids are calculated correctly`() = assertAllEquals(
        Grid(8).cells[3][5] to 4,
        Grid(57).cells[122][79] to -5,
        Grid(39).cells[217][196] to 0,
        Grid(71).cells[101][153] to 4,
    )

    @Test
    fun `Determine the power level of a 3x3 square`() = assertAllEquals(
        Grid(18).powerLevelFor(33, 45) to 29,
        Grid(42).powerLevelFor(21, 61) to 30,
    )

    @Test
    fun `Find the most powerful square`() = assertAllEquals(
        Grid(18).findMostPowerfulSquare().powerLevel to 29,
        Grid(42).findMostPowerfulSquare().powerLevel to 30,
    )

}
