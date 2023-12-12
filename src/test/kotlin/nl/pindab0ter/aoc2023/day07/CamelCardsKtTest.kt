package nl.pindab0ter.aoc2023.day07

import nl.pindab0ter.common.assertAllEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Suppress("SpellCheckingInspection")
@DisplayName("2023 Day 07 - Camel Cards")
class CamelCardsKtTest {

    @Nested
    @DisplayName("Without jokers")
    inner class WithoutJokers {
        private val withJokers = false

        @Test
        fun `Correctly find hand types`() = assertAllEquals(
            Type.FIVE_OF_A_KIND to Type.of("AAAAA", withJokers),
            Type.FOUR_OF_A_KIND to Type.of("AA8AA", withJokers),
            Type.FULL_HOUSE to Type.of("23332", withJokers),
            Type.THREE_OF_A_KIND to Type.of("TTT98", withJokers),
            Type.TWO_PAIR to Type.of("23432", withJokers),
            Type.ONE_PAIR to Type.of("A23A4", withJokers),
            Type.HIGH_CARD to Type.of("23456", withJokers),
        )

        @Test
        fun `Correctly order hands of differing types`() {
            val leastValuableHand = Hand("AA8AA", 0, withJokers)
            val mostValuableHand = Hand("AAAAA", 0, withJokers)

            val actual = listOf(leastValuableHand, mostValuableHand).sorted()
            val expected = listOf(mostValuableHand, leastValuableHand)

            assertEquals(expected, actual)
        }

        @Test
        fun `Don't match a one pair before a two pair`() {
            val mostValuableHand = Hand("22322", 0, withJokers)
            val leastValuableHand = Hand("JQKAA", 0, withJokers)

            val actual = listOf(leastValuableHand, mostValuableHand).sorted()
            val expected = listOf(mostValuableHand, leastValuableHand)

            assertEquals(expected, actual)
        }

        @Test
        fun `Correctly order a hands of the same type`() {
            val mostValuableHand = Hand("KK677", 0, withJokers)
            val leastValuableHand = Hand("KTJJT", 0, withJokers)

            val actual = listOf(leastValuableHand, mostValuableHand).sorted()
            val expected = listOf(mostValuableHand, leastValuableHand)

            assertEquals(expected, actual)
        }

        @Test
        fun `A full house is worth more than a three of a kind`() {
            val mostValuableHand = Hand("Q2Q2Q", 0, withJokers)
            val leastValuableHand = Hand("QQQJA", 0, withJokers)

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

            val actual = parse(input, withJokers).sorted()
            val expected = listOf(
                Hand("QQQJA", 483, withJokers),
                Hand("T55J5", 684, withJokers),
                Hand("KK677", 28, withJokers),
                Hand("KTJJT", 220, withJokers),
                Hand("32T3K", 765, withJokers),
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

            val actual = parse(input, withJokers).sorted()
            val expected = listOf(
                Hand("AAAAA", 59, withJokers),
                Hand("JJJJJ", 31, withJokers),
                Hand("AAAAJ", 53, withJokers),
                Hand("JAAAA", 43, withJokers),
                Hand("JJJJ2", 34, withJokers),
                Hand("2AAAA", 17, withJokers),
                Hand("2JJJJ", 47, withJokers),
                Hand("Q2Q2Q", 13, withJokers),
                Hand("QQQJA", 23, withJokers),
                Hand("T55J5", 19, withJokers),
                Hand("KK677", 11, withJokers),
                Hand("KTJJT", 29, withJokers),
                Hand("32T3K", 7, withJokers),
                Hand("J345A", 3, withJokers),
                Hand("2345A", 2, withJokers),
                Hand("2345J", 5, withJokers),
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

            val hands = parse(input, withJokers)

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

            val hands = parse(input, withJokers)

            assertEquals(4466, hands.totalWinnings())
        }
    }

    @Nested
    @DisplayName("With jokers")
    inner class WithJokers {
        private val withJokers = true

        @Test
        fun `Correctly order a list of hands`() {
            val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()

            val actual = parse(input, withJokers).sorted()
            val expected = listOf(
                Hand("KTJJT", 220, withJokers),
                Hand("QQQJA", 483, withJokers),
                Hand("T55J5", 684, withJokers),
                Hand("KK677", 28, withJokers),
                Hand("32T3K", 765, withJokers),
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

            val actual = parse(input, withJokers).sorted()
            val expected = listOf(
                Hand("AAAAA", 61, withJokers),
                Hand("AAAAJ", 59, withJokers),
                Hand("2JJJJ", 53, withJokers),
                Hand("JAAAA", 43, withJokers),
                Hand("JJJJ2", 41, withJokers),
                Hand("JJJJJ", 37, withJokers),
                Hand("KTJJT", 34, withJokers),
                Hand("QQQJA", 31, withJokers),
                Hand("T55J5", 29, withJokers),
                Hand("2AAAA", 23, withJokers),
                Hand("Q2Q2Q", 19, withJokers),
                Hand("T3T3J", 17, withJokers),
                Hand("Q2KJJ", 13, withJokers),
                Hand("T3Q33", 11, withJokers),
                Hand("KK677", 7, withJokers),
                Hand("32T3K", 5, withJokers),
                Hand("2345J", 3, withJokers),
                Hand("J345A", 2, withJokers),
                Hand("2345A", 1, withJokers),
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

            val hands = parse(input, withJokers)

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

            val hands = parse(input, withJokers)

            assertEquals(6839, hands.totalWinnings())
        }
    }
}