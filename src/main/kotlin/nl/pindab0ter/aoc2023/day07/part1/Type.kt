package nl.pindab0ter.aoc2023.day07.part1

import nl.pindab0ter.common.helpers.grouped
import kotlin.enums.enumEntries

enum class Type {
    FIVE_OF_A_KIND {
        override fun matches(cards: CharSequence) = cards.grouped().size == 1
    },
    FOUR_OF_A_KIND {
        override fun matches(cards: CharSequence) = cards.grouped().any { it.size == 4 }
    },
    FULL_HOUSE {
        override fun matches(cards: CharSequence) = cards.grouped().size == 2
    },
    THREE_OF_A_KIND {
        override fun matches(cards: CharSequence) = cards.grouped().any { it.size == 3 }
    },
    TWO_PAIR {
        override fun matches(cards: CharSequence) = cards.grouped().size == 3
    },
    ONE_PAIR {
        override fun matches(cards: CharSequence) = cards.grouped().size == 4
    },
    HIGH_CARD {
        override fun matches(cards: CharSequence) = true
    };

    abstract fun matches(cards: CharSequence): Boolean

    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        fun of(hand: CharSequence): Type = enumEntries<Type>().first { it.matches(hand) }
    }
}