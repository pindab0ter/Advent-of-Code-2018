fun main() {
    val initialState = ClassLoader.getSystemResource("input").readText().lines().map(::parseLine)

    val finalState = calculateFinalState(initialState)

    print(finalState)
}

fun parseLine(line: String): Star {
    val starRegex = Regex("""position=<\s*(-?\d+),\s*(-?\d+)> velocity=<\s*(-?\d+),\s*(-?\d+)>""")
    val starRegexResults = starRegex.find(line)!!
    val (x, y, velocityX, velocityY) = starRegexResults.destructured
    return Star(x.toInt(), y.toInt(), velocityX.toInt(), velocityY.toInt())
}

data class Star(
    val x: Int,
    val y: Int,
    val velocityX: Int,
    val velocityY: Int,
)

typealias Field = List<Star>

fun Field.width(): Int {
    return maxOf(Star::x) - minOf(Star::x)
}

fun Field.height(): Int {
    return maxOf(Star::y) - minOf(Star::y)
}

tailrec fun calculateFinalState(currentState: Field): Field {
    val newState = advanceTime(currentState)

    return if (newState.width() < currentState.width()) {
        calculateFinalState(newState)
    } else {
        currentState
    }
}

private fun advanceTime(stars: Field): Field {
    return stars.map { star ->
        star.copy(
            x = star.x + star.velocityX,
            y = star.y + star.velocityY
        )
    }
}

private fun print(field: Field) {
    val minX = field.minOf(Star::x)
    val minY = field.minOf(Star::y)
    val width = field.width() + 1
    val height = field.height() + 1

    var grid = ".".repeat(width * height)

    field.forEach { star ->
        val x = (-minX) + star.x
        val y = (-minY) + star.y
        val location = y * width + x
        grid = grid.replaceRange(location..location, "#")
    }

    println(grid.chunked(width).joinToString("\n"))
}
