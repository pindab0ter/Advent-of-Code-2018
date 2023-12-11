package nl.pindab0ter.common

import kotlin.math.abs

fun Int.isOdd(): Boolean = this % 2 != 0

/**
 * Returns the [position]th digit from the right.
 *
 * For example, `123.nthDigitFromRight(2)` returns `2`.
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

/**
 * Greatest common divisor, see [Euclidean algorithm](https://en.wikipedia.org/wiki/Euclidean_algorithm).
 *
 * @return The greatest common divisor of [a] and [b].
 */
tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) abs(a) else gcd(b, a % b)

/**
 * Lowest common multiple.
 *
 * @return The lowest common multiple of [a] and [b].
 */
fun lcm(a: Long, b: Long): Long {
    return abs(a * b) / gcd(a, b)
}

/**
 * Lowest common multiple.
 *
 * @return The lowest common multiple of all [numbers].
 */
fun lcm(vararg numbers: Long): Long = numbers.reduce { a, b -> lcm(a, b) }
