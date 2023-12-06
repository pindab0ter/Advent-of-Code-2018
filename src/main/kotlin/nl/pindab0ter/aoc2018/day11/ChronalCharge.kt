package nl.pindab0ter.aoc2018.day11

import nl.pindab0ter.common.helpers.nthDigitFromRight

private const val SQUARE_SIZE = 3

fun main() {
    val grid = Grid(9005)

    val mostPowerfulSquare = grid.findMostPowerfulSquare()

    println("The most powerful square is at ${mostPowerfulSquare.x},${mostPowerfulSquare.y} with a power level of ${mostPowerfulSquare.powerLevel}")
}

data class Square(val x: Int, val y: Int, val powerLevel: Int)

data class Grid(val width: Int, val height: Int, val cells: List<List<Int>>) {
    private val searchableWidth = width - SQUARE_SIZE + 1
    private val searchableHeight = height - SQUARE_SIZE + 1

    /**
     * Gets the power level of a 3x3 grid starting at [startX] and [startY]
     *
     * @param startX The x coordinate of the top left corner of the grid.
     * @param startY The y coordinate of the top left corner of the grid.
     * @return The power level of the grid.
     */
    fun powerLevelFor(startX: Int, startY: Int): Int {
        return (startY until startY + SQUARE_SIZE).flatMap { y ->
            (startX until startX + SQUARE_SIZE).map { x ->
                cells[x][y]
            }
        }.sum()
    }

    tailrec fun findMostPowerfulSquare(
        index: Int = 0,
        mostPowerfulSquareYet: Square = Square(0, 0, 0)
    ): Square {
        val x = index % searchableWidth
        val y = index / searchableHeight
        val powerLevel = powerLevelFor(x, y)

        if (index + 1 == (searchableWidth) * (searchableHeight)) return mostPowerfulSquareYet

        return if (powerLevel > mostPowerfulSquareYet.powerLevel) {
            findMostPowerfulSquare(index + 1, Square(x, y, powerLevel))
        } else {
            findMostPowerfulSquare(index + 1, mostPowerfulSquareYet)
        }
    }

    companion object {
        operator fun invoke(serialNumber: Int, width: Int = 300, height: Int = 300): Grid {
            val cells = List(width) { x ->
                List(height) { y ->
                    val rackId = x + 10
                    ((((rackId * y) + serialNumber) * rackId).nthDigitFromRight(SQUARE_SIZE) ?: 0) - 5
                }
            }
            return Grid(width, height, cells)
        }
    }
}
