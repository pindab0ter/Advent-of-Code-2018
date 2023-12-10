package nl.pindab0ter.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

/**
 * @return A collection of lists of characters grouped by their value.
 */
fun CharSequence.grouped(): Collection<List<Char>> = groupBy { it }.values

/**
 * Transforms the elements of the iterable asynchronously using the provided [transform] function. The function suspends
 * until all transformations are complete.
 *
 * @param transform The function to apply to each element. This function is invoked asynchronously on a separate coroutine.
 * @return A new iterable with the transformed elements.
 */
fun <T, R> Iterable<T>.mapAsync(transform: (T) -> R): List<R> = runBlocking {
    map {
        async(Dispatchers.Default) {
            transform(it)
        }
    }.awaitAll()
}

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

