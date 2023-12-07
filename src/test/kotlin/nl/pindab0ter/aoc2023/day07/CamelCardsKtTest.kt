package nl.pindab0ter.aoc2023.day07

import nl.pindab0ter.aoc2023.day07.Type.*
import nl.pindab0ter.common.helpers.assertAllEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("2023 Day 07 - Camel Cards")
class CamelCardsKtTest {

    @Test
    fun `Correctly find hand types`() = assertAllEquals(
        Type.of("AAAAA") to FIVE_OF_A_KIND,
        Type.of("AA8AA") to FOUR_OF_A_KIND,
        Type.of("23332") to FULL_HOUSE,
        Type.of("TTT98") to THREE_OF_A_KIND,
        Type.of("23432") to TWO_PAIR,
        Type.of("A23A4") to ONE_PAIR,
        Type.of("23456") to HIGH_CARD,
    )

    @Test
    fun `Correctly order hands of differing types`() {
        val leastValuableHand = Hand("AA8AA", 0)
        val mostValuableHand = Hand("AAAAA", 0)

        val actual = listOf(leastValuableHand, mostValuableHand).sorted()
        val expected = listOf(mostValuableHand, leastValuableHand)

        assertEquals(expected, actual)
    }

    @Test
    fun `Don't match a one pair before a two pair`() {
        val mostValuableHand = Hand("22322", 0)
        val leastValuableHand = Hand("JQKAA", 0)

        val actual = listOf(leastValuableHand, mostValuableHand).sorted()
        val expected = listOf(mostValuableHand, leastValuableHand)

        assertEquals(expected, actual)
    }

    @Test
    fun `Correctly order a hands of the same type`() {
        val mostValuableHand = Hand("KK677", 0)
        val leastValuableHand = Hand("KTJJT", 0)

        val actual = listOf(leastValuableHand, mostValuableHand).sorted()
        val expected = listOf(mostValuableHand, leastValuableHand)

        assertEquals(expected, actual)
    }

    @Test
    fun `A full house is worth more than a three of a kind`() {
        val mostValuableHand = Hand("Q2Q2Q", 0)
        val leastValuableHand = Hand("QQQJA", 0)

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

        val actual = parse(input).sorted()
        val expected = listOf(
            Hand("QQQJA", 483),
            Hand("T55J5", 684),
            Hand("KK677", 28),
            Hand("KTJJT", 220),
            Hand("32T3K", 765),
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

        val actual = parse(input).sorted()
        val expected = listOf(
            Hand("AAAAA", 59),
            Hand("JJJJJ", 31),
            Hand("AAAAJ", 53),
            Hand("JAAAA", 43),
            Hand("JJJJ2", 34),
            Hand("2AAAA", 17),
            Hand("2JJJJ", 47),
            Hand("Q2Q2Q", 13),
            Hand("QQQJA", 23),
            Hand("T55J5", 19),
            Hand("KK677", 11),
            Hand("KTJJT", 29),
            Hand("32T3K", 7),
            Hand("J345A", 3),
            Hand("2345A", 2),
            Hand("2345J", 5),
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

        val hands = parse(input)

        assertEquals(4466, hands.totalWinnings())
    }

}