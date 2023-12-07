package nl.pindab0ter.aoc2023.day07


data class Hand(val cards: CharSequence, val bid: Int) : Comparable<Hand> {
    private val cardValues = mapOf(
        '2' to 2,
        '3' to 3,
        '4' to 4,
        '5' to 5,
        '6' to 6,
        '7' to 7,
        '8' to 8,
        '9' to 9,
        'T' to 10,
        'J' to 11,
        'Q' to 12,
        'K' to 13,
        'A' to 14,
    )

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
            val cardOrdering = cardValues[other.cards[index]]!!.compareTo(cardValues[this.cards[index]]!!)
            if (cardOrdering != 0) return cardOrdering
        }

        return 0
    }

    companion object {
        const val SIZE = 5

        operator fun invoke(input: String) = input.split(" ").let { (hand, bid) -> Hand(hand, bid.toInt()) }
    }
}