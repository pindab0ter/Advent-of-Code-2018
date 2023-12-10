package nl.pindab0ter.aoc2023.day07

import nl.pindab0ter.aoc2023.day07.part2.Hand
import nl.pindab0ter.aoc2023.day07.part2.parse
import nl.pindab0ter.aoc2023.day07.part2.totalWinnings
import nl.pindab0ter.common.assertAllEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import nl.pindab0ter.aoc2023.day07.part1.Hand as Part1Hand
import nl.pindab0ter.aoc2023.day07.part1.Type as Part1Type
import nl.pindab0ter.aoc2023.day07.part1.parse as part1Parse
import nl.pindab0ter.aoc2023.day07.part1.totalWinnings as part1TotalWinnings

@Suppress("SpellCheckingInspection")
@DisplayName("2023 Day 07 - Camel Cards")
class CamelCardsKtTest {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Correctly find hand types`() = assertAllEquals(
            Part1Type.FIVE_OF_A_KIND to Part1Type.of("AAAAA"),
            Part1Type.FOUR_OF_A_KIND to Part1Type.of("AA8AA"),
            Part1Type.FULL_HOUSE to Part1Type.of("23332"),
            Part1Type.THREE_OF_A_KIND to Part1Type.of("TTT98"),
            Part1Type.TWO_PAIR to Part1Type.of("23432"),
            Part1Type.ONE_PAIR to Part1Type.of("A23A4"),
            Part1Type.HIGH_CARD to Part1Type.of("23456"),
        )

        @Test
        fun `Correctly order hands of differing types`() {
            val leastValuableHand = Part1Hand("AA8AA", 0)
            val mostValuableHand = Part1Hand("AAAAA", 0)

            val actual = listOf(leastValuableHand, mostValuableHand).sorted()
            val expected = listOf(mostValuableHand, leastValuableHand)

            assertEquals(expected, actual)
        }

        @Test
        fun `Don't match a one pair before a two pair`() {
            val mostValuableHand = Part1Hand("22322", 0)
            val leastValuableHand = Part1Hand("JQKAA", 0)

            val actual = listOf(leastValuableHand, mostValuableHand).sorted()
            val expected = listOf(mostValuableHand, leastValuableHand)

            assertEquals(expected, actual)
        }

        @Test
        fun `Correctly order a hands of the same type`() {
            val mostValuableHand = Part1Hand("KK677", 0)
            val leastValuableHand = Part1Hand("KTJJT", 0)

            val actual = listOf(leastValuableHand, mostValuableHand).sorted()
            val expected = listOf(mostValuableHand, leastValuableHand)

            assertEquals(expected, actual)
        }

        @Test
        fun `A full house is worth more than a three of a kind`() {
            val mostValuableHand = Part1Hand("Q2Q2Q", 0)
            val leastValuableHand = Part1Hand("QQQJA", 0)

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

            val actual = part1Parse(input).sorted()
            val expected = listOf(
                Part1Hand("QQQJA", 483),
                Part1Hand("T55J5", 684),
                Part1Hand("KK677", 28),
                Part1Hand("KTJJT", 220),
                Part1Hand("32T3K", 765),
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

            val actual = part1Parse(input).sorted()
            val expected = listOf(
                Part1Hand("AAAAA", 59),
                Part1Hand("JJJJJ", 31),
                Part1Hand("AAAAJ", 53),
                Part1Hand("JAAAA", 43),
                Part1Hand("JJJJ2", 34),
                Part1Hand("2AAAA", 17),
                Part1Hand("2JJJJ", 47),
                Part1Hand("Q2Q2Q", 13),
                Part1Hand("QQQJA", 23),
                Part1Hand("T55J5", 19),
                Part1Hand("KK677", 11),
                Part1Hand("KTJJT", 29),
                Part1Hand("32T3K", 7),
                Part1Hand("J345A", 3),
                Part1Hand("2345A", 2),
                Part1Hand("2345J", 5),
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

            val hands = part1Parse(input)

            assertEquals(6440, hands.part1TotalWinnings())
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

            val hands = part1Parse(input)

            assertEquals(4466, hands.part1TotalWinnings())
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Correctly order a list of hands`() {
            val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()

            val actual = parse(input).sorted()
            val expected = listOf(
                Hand("KTJJT", 220),
                Hand("QQQJA", 483),
                Hand("T55J5", 684),
                Hand("KK677", 28),
                Hand("32T3K", 765),
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

            val actual = parse(input).sorted()
            val expected = listOf(
                Hand("AAAAA", 61),
                Hand("AAAAJ", 59),
                Hand("2JJJJ", 53),
                Hand("JAAAA", 43),
                Hand("JJJJ2", 41),
                Hand("JJJJJ", 37),
                Hand("KTJJT", 34),
                Hand("QQQJA", 31),
                Hand("T55J5", 29),
                Hand("2AAAA", 23),
                Hand("Q2Q2Q", 19),
                Hand("T3T3J", 17),
                Hand("Q2KJJ", 13),
                Hand("T3Q33", 11),
                Hand("KK677", 7),
                Hand("32T3K", 5),
                Hand("2345J", 3),
                Hand("J345A", 2),
                Hand("2345A", 1),
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

            val hands = parse(input)

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

            val hands = parse(input)

            assertEquals(6839, hands.totalWinnings())
        }

    }

}