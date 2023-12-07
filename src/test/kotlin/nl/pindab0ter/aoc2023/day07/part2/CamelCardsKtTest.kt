package nl.pindab0ter.aoc2023.day07.part2

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("2023 Day 07 - Camel Cards - Part two")
class CamelCardsKtTest {

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