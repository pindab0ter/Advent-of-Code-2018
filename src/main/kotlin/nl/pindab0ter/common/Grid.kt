package nl.pindab0ter.common

class Grid<T>(grid: List<List<T>>) {
    val rows: List<List<T>> = grid
    val columns: List<List<T>> = grid[0].indices.map { x -> grid.indices.map { y -> grid[y][x] } }
    val width: Int = rows.size
    val height: Int = columns.size

    fun row(y: Int): List<T> = rows[y]
    fun column(x: Int): List<T> = columns[x]

    init {
        require(rows.all { it.size == rows.first().size })
        require(columns.all { it.size == columns.first().size })
    }

    override fun toString(): String = rows.joinToString("\n") { row -> row.joinToString(", ") }
}
