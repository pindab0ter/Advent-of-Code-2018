@file:Suppress("SpellCheckingInspection")

package nl.pindab0ter.aoc2023.day01

fun main() {
    val input = ClassLoader.getSystemResource("2023/day01/input").readText()
    val result = input.lines().sumOf(::getCalibrationValue)

    println(result)
}

val digitNames = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

fun getCalibrationValue(line: String): Int {
    val digitRegex = Regex("""(one|two|three|four|five|six|seven|eight|nine|\d)""")
    val matchFirst = digitRegex.find(line)!!.value

    // Reversed digit regex to cover edge cases like "eightwo", as Regex doesn't support overlapping matches.
    // Thanks, Bart
    val reversedDigitRegex = Regex("""(eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|\d)""")
    val matchLast = reversedDigitRegex.find(line.reversed())?.value?.reversed()

    val firstDigit = digitNames[matchFirst] ?: matchFirst
    val lastDigit = when (matchLast) {
        null -> firstDigit
        else -> digitNames[matchLast] ?: matchLast
    }

    return "${firstDigit}${lastDigit}".toInt()
}
