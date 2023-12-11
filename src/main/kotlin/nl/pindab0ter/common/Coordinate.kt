package nl.pindab0ter.common

data class Coordinate(val x: Int, val y: Int)

fun Set<Coordinate>.contains(x: Int, y: Int) = contains(Coordinate(x, y))