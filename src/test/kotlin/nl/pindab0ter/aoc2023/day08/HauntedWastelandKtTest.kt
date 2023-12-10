package nl.pindab0ter.aoc2023.day08

import nl.pindab0ter.common.helpers.assertAllEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("2023 Day 08 - Haunted Wasteland")
class HauntedWastelandKtTest {

    @Test
    fun `Find the amount of steps it takes to reach the destination`() {
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
            findStepsToDestination(instructions1, network1, { equals("AAA") }, { equals("ZZZ") }) to 2,
            findStepsToDestination(instructions2, network2, { equals("AAA") }, { equals("ZZZ") }) to 6,
        )
    }

    @Test
    fun `Find the amount of steps it takes for all routes to reach the destination`() {
        val input = """
            LR

            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
            """.trimIndent()

        val (instructions, network) = parse(input)

        val actual = findStepsToDestination(instructions, network, { endsWith('A') }, { endsWith('Z') })

        assertEquals(6, actual)
    }
}