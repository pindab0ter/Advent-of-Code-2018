package nl.pindab0ter.aoc2024.day05

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("2024 Day 05 - Print Queue")
class PrintQueueKtTest {
    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("ruleAndUpdateProvider")
    fun `Check if an update conforms to the page ordering rule`(
        expected: Boolean,
        rule: PageOrderingRule,
        update: Update,
    ) {
        val actual = update.conformsTo(rule)

        assertEquals(expected, actual)
    }

    @Test
    fun `Count the amount of updates that are correctly ordered`() {
        val input = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent()

        val (pageOrderingRules, updates) = input.parse()

        val result = updates.filter { update ->
            pageOrderingRules.all { rule ->
                update.conformsTo(rule)
            }
        }

        assertEquals(3, result.count())
    }

    companion object {
        @JvmStatic
        fun ruleAndUpdateProvider(): Stream<Arguments> = Stream.of(
            arguments(true, Pair(47, 53), listOf(75, 47, 61, 53, 29)),
            arguments(true, Pair(10, 99), listOf(75, 47, 61, 53, 29)),
            arguments(false, Pair(97, 75), listOf(75, 97, 47, 61, 53)),
            arguments(true, Pair(61, 13), listOf(75, 47, 61, 53, 29)),
        )
    }
}
