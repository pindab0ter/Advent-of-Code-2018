package nl.pindab0ter.aoc2018.day07

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2018 Day07 - Sum Of Its Parts")
class SumOfItsPartsKtTest {

    private val input = listOf(
        'C' to 'A',
        'C' to 'F',
        'A' to 'B',
        'A' to 'D',
        'B' to 'E',
        'D' to 'E',
        'F' to 'E'
    )

    @Test
    fun correctOrder() {
        val actual = SumOfItsParts(input).correctOrder()
        Assertions.assertEquals("CABDFE", actual)
    }

    @Test
    fun teamWork() {
        val actual = SumOfItsParts(input).teamwork(workers = 2, timePerStep = 0)
        Assertions.assertEquals(15, actual)
    }
}