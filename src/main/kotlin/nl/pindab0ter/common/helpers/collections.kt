package nl.pindab0ter.common.helpers

/**
 * @return A collection of lists of characters grouped by their value.
 */
fun CharSequence.grouped(): Collection<List<Char>> = groupBy { it }.values

/**
 * Returns the product of the result of [selector] for each element in the iterable.
 *
 * @param selector The function to apply to each element.
 * @return The product of the result of [selector] for each element in the iterable.
 */
fun <T> Iterable<T>.productOf(selector: (T) -> Int): Int = fold(1) { acc, element -> acc * selector(element) }

/**
 * @return Everything but the first element of the iterable.
 */
fun <T> Iterable<T>.tail(): Iterable<T> = drop(1)

