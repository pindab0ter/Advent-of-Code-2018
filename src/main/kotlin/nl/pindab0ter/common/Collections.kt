package nl.pindab0ter.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

/**
 * @return True if all elements in the iterable are equal, false otherwise.
 */
fun <T> Iterable<T>.allElementsEqual(): Boolean = toSet().size == 1

/**
 * @return A collection of lists of characters grouped by their value.
 */
fun CharSequence.grouped(): Collection<List<Char>> = groupBy { it }.values

/**
 * @return The first element in the iterable.
 */
fun <T> Iterable<T>.head(): T = first()

/**
 * @return The second element in the iterable.
 */
fun <T> Iterable<T>.second(): T = elementAt(1)

/**
 * @return A lazy sequence of the results of applying the given [transform] function to the receiver, and then applying
 * the function to the result, and so on.
 */
fun <T> T.iterate(transform: (T) -> T): Sequence<T> = sequence {
    var acc = this@iterate
    while (true) {
        yield(acc)
        acc = transform(acc)
    }
}

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
 * @return A new iterable with all elements after the first.
 */
fun <T> Iterable<T>.tail(): Iterable<T> = drop(1)

/**
 * @return A new iterable without the element at [index].
 */
fun <T> Iterable<T>.without(index: Int) = take(index) + drop(index + 1)
