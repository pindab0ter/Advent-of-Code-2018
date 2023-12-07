package nl.pindab0ter.aoc2023.day07.part1


data class Hand(val cards: CharSequence, val bid: Int) : Comparable<Hand> {

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
        val typeOrdering = Type.of(this.cards).compareTo(Type.of(other.cards))
        if (typeOrdering != 0) return typeOrdering

        (0 until SIZE).forEach { index ->
            val cardOrdering = VALUES.indexOf(other.cards[index]).compareTo(VALUES.indexOf(this.cards[index]))
            if (cardOrdering != 0) return cardOrdering
        }

        return 0
    }

    companion object {
        const val SIZE = 5

        @Suppress("SpellCheckingInspection")
        private const val VALUES = "23456789TJQKA"

        operator fun invoke(input: String) = input.split(" ").let { (hand, bid) -> Hand(hand, bid.toInt()) }
    }
}