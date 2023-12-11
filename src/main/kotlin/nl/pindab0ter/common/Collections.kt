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
 * @return Coordinates (x, y) of the first element matching the given predicate, or null if the collection does not
 * contain such element.
 */
fun <T> Iterable<Iterable<T>>.coordinatesOfFirst(predicate: (T) -> Boolean): Coordinate? {
    for ((y, row) in withIndex()) {
        for ((x, cell) in row.withIndex()) {
            if (predicate(cell)) return Coordinate(x.toLong(), y.toLong())
        }
    }

    return null
}

/**
 * @return The first element matching the given predicate, or null if no such element was found.
 */
fun <T> Iterable<Iterable<T>>.find(predicate: (T) -> Boolean): T? = flatten().find(predicate)

/**
 * @return The element at the given [coordinate], or null if the coordinates are out of bounds.
 * @see Coordinate
 */
operator fun <T> List<List<T>>.get(coordinate: Coordinate): T? = this
    .getOrNull(coordinate.y.toInt())
    ?.getOrNull(coordinate.x.toInt())

/**
 * @return The element at the given [x] and [y] coordinate, or null if the coordinates are out of bounds.
 * @see Coordinate
 */
operator fun <T> List<List<T>>.get(x: Int, y: Int): T? = getOrNull(y)?.getOrNull(x)

/**
 * @return The element at the given [x] and [y] coordinate, or null if the coordinates are out of bounds.
 * @see Coordinate
 */
operator fun <T> List<List<T>>.get(x: Long, y: Long): T? = this[x.toInt(), y.toInt()]

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

