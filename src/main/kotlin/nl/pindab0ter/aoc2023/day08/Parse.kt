package nl.pindab0ter.aoc2023.day08

fun parse(input: String): Pair<String, Network> {
    val nodeRegex = Regex("""^([A-Z\d]{3}) = \(([A-Z\d]{3}), ([A-Z\d]{3})\)$""")
    return input.split("\n\n").let { (directions, nodes) ->
        directions to nodes.lines().associate { line ->
            nodeRegex
                .find(line)!!
                .groupValues
                .let { (_, location, left, right) ->
                    location to Node(left, right)
                }
        }
    }
}