package nl.pindab0ter.aoc2023.day08

import nl.pindab0ter.common.helpers.assertAllEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2023 Day 08 - Haunted Wasteland")
class HauntedWastelandKtTest {

    @Test
    fun `Find the amount of steps it takes to reach ZZZ`() {
        val (instructions1, network1) = parse(
            """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()
        )

        val (instructions2, network2) = parse(
            """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()
        )

        assertAllEquals(
            findDestination(instructions1, network1) to 2,
            findDestination(instructions2, network2) to 6,
        )
    }
}