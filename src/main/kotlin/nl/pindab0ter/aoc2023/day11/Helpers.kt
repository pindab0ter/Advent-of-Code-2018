package nl.pindab0ter.aoc2023.day11

import nl.pindab0ter.common.Coordinate
import nl.pindab0ter.common.tail

fun <T> Set<T>.allCombinations(): Set<Pair<T, T>> = withIndex().flatMap { (i, a) ->
    drop(i + 1).map { b -> a to b }
}.toSet()

fun Set<Coordinate>.expand(): Set<Coordinate> {
    val (emptyRows, emptyColumns) = emptyRowsAndColumns()

    return map { galaxy ->
        galaxy.copy(
            x = galaxy.x + emptyColumns.filter { it.last < galaxy.x }.sumOf(IntRange::count),
            y = galaxy.y + emptyRows.filter { it.last < galaxy.y }.sumOf(IntRange::count)
        )
    }.toSet()
}

fun Set<Int>.contiguousToRanges(): Set<IntRange> = tail().fold(listOf(IntRange(first(), first()))) { acc, x ->
    val currentRange: IntRange = acc.last()
    if (currentRange.last + 1 == x) acc.dropLast(1).plusElement(IntRange(currentRange.first, x))
    else acc.plusElement(x..x)
}.toSet()

fun Set<Coordinate>.emptyRowsAndColumns(): Pair<Set<IntRange>, Set<IntRange>> = Pair(
    first = (minOf(Coordinate::x)..(maxOf(Coordinate::x)))
        .filter { y -> none { it.y == y } }.toSet().contiguousToRanges(),
    second = (minOf(Coordinate::y)..(maxOf(Coordinate::y)))
        .filter { x -> none { it.x == x } }.toSet().contiguousToRanges()
)