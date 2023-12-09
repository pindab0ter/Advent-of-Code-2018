package nl.pindab0ter.aoc2023.day08

import nl.pindab0ter.common.helpers.getInput

const val START = "AAA"
const val DESTINATION = "ZZZ"

fun main() {
    val (instructions, network) = parse(getInput(2023, 8))
    val steps = findDestination(instructions, network)

    println("Found $DESTINATION in $steps steps")
}

typealias Network = Map<String, Node>

data class Node(val left: String, val right: String)

fun parse(input: String): Pair<String, Network> {
    val nodeRegex = Regex("""^([A-Z]{3}) = \(([A-Z]{3}), ([A-Z]{3})\)$""")
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

fun findDestination(instructions: String, network: Network): Int {
    // Accidentally reinvented the `when` statement here.
    val applyInstructions: Map<Char, (Node) -> String> = mapOf('L' to Node::left, 'R' to Node::right)

    tailrec fun goTo(currentNode: Node, index: Int = 0): Int {
        val instruction = instructions[index % instructions.length]

        return when (val nextNode = applyInstructions[instruction]!!(currentNode)) {
            DESTINATION -> index + 1
            else -> goTo(network.getValue(nextNode), index + 1)
        }
    }

    return goTo(network.getValue(START))
}
