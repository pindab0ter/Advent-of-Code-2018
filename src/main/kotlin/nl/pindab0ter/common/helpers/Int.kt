package nl.pindab0ter.common.helpers

/**
 * Returns the [position]th digit from the right.
 *
 * @param position The position of the digit to extract.
 * @return The [position]th digit from the right, or null if the [position] is out of range.
 */
fun Int.nthDigitFromRight(position: Int): Int? {
    if (position < 1) return null
    if (position > this.toString().length) return null

    val base = (0 until position - 1).fold(1) { acc, _ -> acc * 10 }
    return (this / base) % 10
}
