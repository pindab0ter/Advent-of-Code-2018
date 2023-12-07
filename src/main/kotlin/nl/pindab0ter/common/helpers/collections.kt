package nl.pindab0ter.common.helpers

/**
 * @return Everything but the first element of the iterable.
 */
fun <T> Iterable<T>.tail(): Iterable<T> = drop(1)

/**
 * Returns the product of the result of [selector] for each element in the iterable.
 *
 * @param selector The function to apply to each element.
 * @return The product of the result of [selector] for each element in the iterable.
 */
fun <T> Iterable<T>.productOf(selector: (T) -> Int): Int = fold(1) { acc, element -> acc * selector(element) }