package nl.pindab0ter.common

import kotlin.math.abs

/**
 * Represents a 2D coordinate in a [Cartesian](https://simple.wikipedia.org/wiki/Cartesian_coordinate_system) plane.
 */
data class Coordinate(val x: Int, val y: Int)

/**
 * @return `true` if the set contains the coordinate, `false` otherwise.
 */
fun Set<Coordinate>.contains(x: Int, y: Int) = contains(Coordinate(x, y))

/**
 * The Manhattan distance is the shortest distance between two points on a
 * [Cartesian](https://simple.wikipedia.org/wiki/Cartesian_coordinate_system) plane.
 *
 * @return The Manhattan distance between the two [Coordinate]s.
 */
fun manhattanDistance(from: Coordinate, to: Coordinate) = abs(from.x - to.x) + abs(from.y - to.y)