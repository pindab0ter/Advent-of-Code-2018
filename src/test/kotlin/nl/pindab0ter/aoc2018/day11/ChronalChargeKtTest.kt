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
        Grid(18).powerLevelFor(33, 45, 3) to 29,
        Grid(42).powerLevelFor(21, 61, 3) to 30,
    )

    @Test
    fun `Find the most powerful square`() = assertAllEquals(
        Grid(18).findMostPowerfulSquare(3).powerLevel to 29,
        Grid(42).findMostPowerfulSquare(3).powerLevel to 30,
        Grid(18).findMostPowerfulSquare(16).powerLevel to 113,
        Grid(42).findMostPowerfulSquare(12).powerLevel to 119,
    )

    @Test
    fun `Find the most powerful square of any size`() = assertAllEquals(
        // TODO: Move size into square
        Grid(18).findMostPowerfulSquareOfAnySize() to (Square(90,269,113) to 16),
        Grid(42).findMostPowerfulSquareOfAnySize() to (Square(232,251,119) to 12),
    )
}
