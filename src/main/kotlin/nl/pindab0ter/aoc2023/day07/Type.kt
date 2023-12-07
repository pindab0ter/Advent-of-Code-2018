package nl.pindab0ter.aoc2023.day07

import nl.pindab0ter.common.helpers.group
import kotlin.enums.enumEntries

enum class Type {
    FIVE_OF_A_KIND {
        override fun matches(cards: CharSequence) = cards.all { it == cards.first() }
    },
    FOUR_OF_A_KIND {
        override fun matches(cards: CharSequence) = cards.group().any { it.size == 4 }
    },
    FULL_HOUSE {
        override fun matches(cards: CharSequence) = cards.group().let { cardGroups ->
            cardGroups.any { it.size == 3 } && cardGroups.any { it.size == 2 }
        }
    },
    THREE_OF_A_KIND {
        override fun matches(cards: CharSequence) = cards.group().any { it.size == 3 }
    },
    TWO_PAIR {
        override fun matches(cards: CharSequence) = cards.group().count { it.size == 2 } == 2
    },
    ONE_PAIR {
        override fun matches(cards: CharSequence) = cards.group().any { it.size == 2 }
    },
    HIGH_CARD {
        override fun matches(cards: CharSequence) = cards.group().size == 5
    };

    abstract fun matches(cards: CharSequence): Boolean

    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        fun of(hand: CharSequence): Type = enumEntries<Type>().first { it.matches(hand) }
    }
}