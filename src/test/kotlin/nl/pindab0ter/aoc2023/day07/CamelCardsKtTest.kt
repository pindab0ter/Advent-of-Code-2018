package nl.pindab0ter.aoc2023.day07

import nl.pindab0ter.aoc2023.day07.Type.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@Suppress("SpellCheckingInspection")
@DisplayName("2023 Day 07 - Camel Cards")
class CamelCardsKtTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("Without jokers")
    inner class WithoutJokers {

        private fun handTypesProvider(): Stream<Arguments> = Stream.of(
            arguments(FIVE_OF_A_KIND, "AAAAA"),
            arguments(FOUR_OF_A_KIND, "AA8AA"),
            arguments(FULL_HOUSE, "23332"),
            arguments(THREE_OF_A_KIND, "TTT98"),
            arguments(TWO_PAIR, "23432"),
            arguments(ONE_PAIR, "A23A4"),
            arguments(HIGH_CARD, "23456"),
        )

        @ParameterizedTest(name = "{1} → {0}")
        @MethodSource("handTypesProvider")
        fun `Correctly find hand types`(expected: Type, input: String) {
            assertEquals(expected, Type.of(input, WITHOUT_JOKERS))
        }

        @Test
        fun `Correctly order hands of differing types`() {
            val leastValuableHand = Hand("AA8AA", 0, WITHOUT_JOKERS)
            val mostValuableHand = Hand("AAAAA", 0, WITHOUT_JOKERS)

            val actual = listOf(leastValuableHand, mostValuableHand).sorted()
            val expected = listOf(mostValuableHand, leastValuableHand)

            assertEquals(expected, actual)
        }

        @Test
        fun `Don't match a one pair before a two pair`() {
            val mostValuableHand = Hand("22322", 0, WITHOUT_JOKERS)
            val leastValuableHand = Hand("JQKAA", 0, WITHOUT_JOKERS)

            val actual = listOf(leastValuableHand, mostValuableHand).sorted()
            val expected = listOf(mostValuableHand, leastValuableHand)

            assertEquals(expected, actual)
        }

        @Test
        fun `Correctly order a hands of the same type`() {
            val mostValuableHand = Hand("KK677", 0, WITHOUT_JOKERS)
            val leastValuableHand = Hand("KTJJT", 0, WITHOUT_JOKERS)

            val actual = listOf(leastValuableHand, mostValuableHand).sorted()
            val expected = listOf(mostValuableHand, leastValuableHand)

            assertEquals(expected, actual)
        }

        @Test
        fun `A full house is worth more than a three of a kind`() {
            val mostValuableHand = Hand("Q2Q2Q", 0, WITHOUT_JOKERS)
            val leastValuableHand = Hand("QQQJA", 0, WITHOUT_JOKERS)

            val actual = listOf(leastValuableHand, mostValuableHand).sorted()
            val expected = listOf(mostValuableHand, leastValuableHand)

            assertEquals(expected, actual)
        }

        @Test
        fun `Correctly order a list of hands`() {
            val input = """
                32T3K 765
                T55J5 684
                KK677 28
                KTJJT 220
                QQQJA 483
            """.trimIndent()

            val actual = parse(input, WITHOUT_JOKERS).sorted()
            val expected = listOf(
                Hand("QQQJA", 483, WITHOUT_JOKERS),
                Hand("T55J5", 684, WITHOUT_JOKERS),
                Hand("KK677", 28, WITHOUT_JOKERS),
                Hand("KTJJT", 220, WITHOUT_JOKERS),
                Hand("32T3K", 765, WITHOUT_JOKERS),
            )

            assertEquals(expected, actual)
        }

        @Test
        fun `Correctly order a list of hands with more edge cases`() {
            val input = """
                2345A 2
                Q2Q2Q 13
                2345J 5
                J345A 3
                32T3K 7
                T55J5 19
                KK677 11
                KTJJT 29
                QQQJA 23
                JJJJJ 31
                JAAAA 43
                AAAAJ 53
                AAAAA 59
                2AAAA 17
                2JJJJ 47
                JJJJ2 34
            """.trimIndent()

            val actual = parse(input, WITHOUT_JOKERS).sorted()
            val expected = listOf(
                Hand("AAAAA", 59, WITHOUT_JOKERS),
                Hand("JJJJJ", 31, WITHOUT_JOKERS),
                Hand("AAAAJ", 53, WITHOUT_JOKERS),
                Hand("JAAAA", 43, WITHOUT_JOKERS),
                Hand("JJJJ2", 34, WITHOUT_JOKERS),
                Hand("2AAAA", 17, WITHOUT_JOKERS),
                Hand("2JJJJ", 47, WITHOUT_JOKERS),
                Hand("Q2Q2Q", 13, WITHOUT_JOKERS),
                Hand("QQQJA", 23, WITHOUT_JOKERS),
                Hand("T55J5", 19, WITHOUT_JOKERS),
                Hand("KK677", 11, WITHOUT_JOKERS),
                Hand("KTJJT", 29, WITHOUT_JOKERS),
                Hand("32T3K", 7, WITHOUT_JOKERS),
                Hand("J345A", 3, WITHOUT_JOKERS),
                Hand("2345A", 2, WITHOUT_JOKERS),
                Hand("2345J", 5, WITHOUT_JOKERS),
            )

            assertEquals(actual, expected)
        }

        @Test
        fun `Calculate the total winnings of a list of hands`() {
            val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()

            val hands = parse(input, WITHOUT_JOKERS)

            assertEquals(6440, hands.totalWinnings())
        }

        @Test
        fun `Calculate the total winnings of a list of hands with more edge cases`() {
            val input = """
            2345A 2
            Q2Q2Q 13
            2345J 5
            J345A 3
            32T3K 7
            T55J5 19
            KK677 11
            KTJJT 29
            QQQJA 23
            JJJJJ 31
            JAAAA 43
            AAAAJ 53
            AAAAA 59
            2AAAA 17
            2JJJJ 47
            JJJJ2 34
        """.trimIndent()

            val hands = parse(input, WITHOUT_JOKERS)

            assertEquals(4466, hands.totalWinnings())
        }
    }

    @Nested
    @DisplayName("With jokers")
    inner class WithJokers {

        @Test
        fun `Correctly order a list of hands`() {
            val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()

            val actual = parse(input, WITH_JOKERS).sorted()
            val expected = listOf(
                Hand("KTJJT", 220, WITH_JOKERS),
                Hand("QQQJA", 483, WITH_JOKERS),
                Hand("T55J5", 684, WITH_JOKERS),
                Hand("KK677", 28, WITH_JOKERS),
                Hand("32T3K", 765, WITH_JOKERS),
            )

            assertEquals(expected, actual)
        }

        @Test
        fun `Correctly order a list of hands with more edge cases`() {
            val input = """
            2345A 1
            Q2KJJ 13
            Q2Q2Q 19
            T3T3J 17
            T3Q33 11
            2345J 3
            J345A 2
            32T3K 5
            T55J5 29
            KK677 7
            KTJJT 34
            QQQJA 31
            JJJJJ 37
            JAAAA 43
            AAAAJ 59
            AAAAA 61
            2AAAA 23
            2JJJJ 53
            JJJJ2 41
        """.trimIndent()

            val actual = parse(input, WITH_JOKERS).sorted()
            val expected = listOf(
                Hand("AAAAA", 61, WITH_JOKERS),
                Hand("AAAAJ", 59, WITH_JOKERS),
                Hand("2JJJJ", 53, WITH_JOKERS),
                Hand("JAAAA", 43, WITH_JOKERS),
                Hand("JJJJ2", 41, WITH_JOKERS),
                Hand("JJJJJ", 37, WITH_JOKERS),
                Hand("KTJJT", 34, WITH_JOKERS),
                Hand("QQQJA", 31, WITH_JOKERS),
                Hand("T55J5", 29, WITH_JOKERS),
                Hand("2AAAA", 23, WITH_JOKERS),
                Hand("Q2Q2Q", 19, WITH_JOKERS),
                Hand("T3T3J", 17, WITH_JOKERS),
                Hand("Q2KJJ", 13, WITH_JOKERS),
                Hand("T3Q33", 11, WITH_JOKERS),
                Hand("KK677", 7, WITH_JOKERS),
                Hand("32T3K", 5, WITH_JOKERS),
                Hand("2345J", 3, WITH_JOKERS),
                Hand("J345A", 2, WITH_JOKERS),
                Hand("2345A", 1, WITH_JOKERS),
            )

            assertEquals(actual, expected)
        }

        @Test
        fun `Calculate the total winnings of a list of hands`() {
            val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()

            val hands = parse(input, WITH_JOKERS)

            assertEquals(5905, hands.totalWinnings())
        }

        @Test
        fun `Calculate the total winnings of a list of hands with more edge cases`() {
            val input = """
            2345A 1
            Q2KJJ 13
            Q2Q2Q 19
            T3T3J 17
            T3Q33 11
            2345J 3
            J345A 2
            32T3K 5
            T55J5 29
            KK677 7
            KTJJT 34
            QQQJA 31
            JJJJJ 37
            JAAAA 43
            AAAAJ 59
            AAAAA 61
            2AAAA 23
            2JJJJ 53
            JJJJ2 41
        """.trimIndent()

            val hands = parse(input, WITH_JOKERS)

            assertEquals(6839, hands.totalWinnings())
        }
    }

    companion object {
        private const val WITH_JOKERS = true
        private const val WITHOUT_JOKERS = false
    }
}