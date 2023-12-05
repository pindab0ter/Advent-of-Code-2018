package nl.pindab0ter.aoc2018.day10

fun main() {
    val initialState = ClassLoader.getSystemResource("2018/day10/input").readText().lines().map(::parseLine)
    val (finalState, secondsPassed) = calculateFinalState(initialState)

    println(finalState)
    println("Seconds passed: $secondsPassed")
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

tailrec fun calculateFinalState(currentState: Field, secondsPassed: Int = 0): Pair<Field, Int> {
    val newState = advanceTime(currentState)

    return if (newState.width() < currentState.width()) {
        calculateFinalState(newState, secondsPassed + 1)
    } else {
        currentState to secondsPassed
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