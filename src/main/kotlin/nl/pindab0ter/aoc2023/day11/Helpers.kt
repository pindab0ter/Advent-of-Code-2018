package nl.pindab0ter.aoc2023.day11

import nl.pindab0ter.common.Coordinate
import nl.pindab0ter.common.manhattanDistance
import nl.pindab0ter.common.tail
import kotlin.math.max

/**
 * @return The sum of the distances between all [Coordinate]s in the expanded galaxy.
 */
fun Set<Coordinate>.sumOfDistancesBetweenGalaxies(expansionMultiplier: Long = 1L): Long =
    expand(expansionMultiplier).distancesBetween().sum()

/**
 * @return The distances between all [Coordinate]s in the set.
 */
fun Set<Coordinate>.distancesBetween() = allCombinations().map { (a, b) -> manhattanDistance(a, b) }

/**
 * @return All unique combinations of two [Coordinate]s in the set.
 */
fun <T> Set<T>.allCombinations(): Set<Pair<T, T>> = withIndex().flatMap { (i, a) ->
    drop(i + 1).map { b -> a to b }
}.toSet()

/**
 * Expands empty space in the set of [Coordinate]s by increasing the x and y coordinates of the galaxies in the [Set].
 *
 * @return The [Set] of [Coordinate]s with their x and y coordinates increased by the amount of extra empty space.
 */
fun Set<Coordinate>.expand(multiplier: Long = 1): Set<Coordinate> {
    // Find the empty rows and columns, saving them as ranges of contiguous rows and columns
    val emptyRows: Set<LongRange> = (minOf(Coordinate::x)..(maxOf(Coordinate::x)))
        .filter { y -> none { it.y == y } }.toSet().contiguousToRanges()
    val emptyCols: Set<LongRange> = (minOf(Coordinate::y)..(maxOf(Coordinate::y)))
        .filter { x -> none { it.x == x } }.toSet().contiguousToRanges()

    // Add the empty space to the coordinates, multiplying the amount of empty space by the multiplier
    return map { galaxy ->
        galaxy.copy(
            x = galaxy.x + (emptyCols.filter { it.last < galaxy.x }.sumOf(LongRange::count) * max(1L, multiplier - 1L)),
            y = galaxy.y + (emptyRows.filter { it.last < galaxy.y }.sumOf(LongRange::count) * max(1L, multiplier - 1L)),
        )
    }.toSet()
}

/**
 * Combines contiguous ranges of [Long]s into [LongRange]s. E.g.: `[1, 2, 4, 5]` becomes `[1..2, 4..5]`.
 *
 * @return The ranges of contiguous numbers in the set.
 */
fun Set<Long>.contiguousToRanges(): Set<LongRange> = tail().fold(listOf(LongRange(first(), first()))) { acc, x ->
    val currentRange: LongRange = acc.last()
    if (currentRange.last + 1L == x) acc.dropLast(1).plusElement(LongRange(currentRange.first, x))
    else acc.plusElement(x..x)
}.toSet()
