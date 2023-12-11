package nl.pindab0ter.common

import kotlin.math.abs

/**
 * Represents a 2D coordinate in a [Cartesian](https://simple.wikipedia.org/wiki/Cartesian_coordinate_system) plane.
 */
data class Coordinate(val x: Long, val y: Long) {
    companion object {
        operator fun invoke(x: Int, y: Int) = Coordinate(x.toLong(), y.toLong())
    }
}

/**
 * @return `true` if the set contains the coordinate, `false` otherwise.
 */
fun Set<Coordinate>.contains(x: Long, y: Long) = contains(Coordinate(x, y))

/**
 * @return `true` if the set contains the coordinate, `false` otherwise.
 */
fun Set<Coordinate>.contains(x: Int, y: Int) = contains(x.toLong(), y.toLong())

/**
 * The Manhattan distance is the shortest distance between two points on a
 * [Cartesian](https://simple.wikipedia.org/wiki/Cartesian_coordinate_system) plane.
 *
 * @return The Manhattan distance between the two [Coordinate]s.
 */
fun manhattanDistance(from: Coordinate, to: Coordinate) = abs(from.x - to.x) + abs(from.y - to.y)