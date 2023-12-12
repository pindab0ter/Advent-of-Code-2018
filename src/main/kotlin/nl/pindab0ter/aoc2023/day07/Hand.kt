package nl.pindab0ter.aoc2023.day07


data class Hand(val cards: CharSequence, val bid: Int, val withJokers: Boolean) : Comparable<Hand> {

    /**
     * Compare this hand to another hand.
     *
     * The comparison is done by comparing the type of the hand first, and then the cards in the hand.
     *
     * Most valuable hand first.
     *
     * @param other The other hand to compare this hand to.
     */
    override fun compareTo(other: Hand): Int {
        val typeOrdering = Type.of(this.cards, withJokers).compareTo(Type.of(other.cards, withJokers))
        if (typeOrdering != 0) return typeOrdering

        val values = if (withJokers) VALUES_WITH_JOKERS else VALUES_WITHOUT_JOKERS

        (0 until SIZE).forEach { index ->
            val cardOrdering = values.indexOf(other.cards[index]).compareTo(values.indexOf(this.cards[index]))
            if (cardOrdering != 0) return cardOrdering
        }

        return 0
    }

    @Suppress("SpellCheckingInspection")
    companion object {
        const val SIZE = 5

        private const val VALUES_WITHOUT_JOKERS = "23456789TJQKA"
        private const val VALUES_WITH_JOKERS = "J23456789TQKA"

        operator fun invoke(input: String, withJokers: Boolean) = input
            .split(" ")
            .let { (hand, bid) ->
                Hand(hand, bid.toInt(), withJokers)
            }
    }
}