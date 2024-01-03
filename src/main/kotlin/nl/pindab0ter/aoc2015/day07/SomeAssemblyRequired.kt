package nl.pindab0ter.aoc2015.day07

import nl.pindab0ter.common.*
import kotlin.io.println


data class Instruction(
    val a: Either<UShort, String>?,
    val operator: Operator?,
    val b: Either<UShort, String>?,
)

private val regex = Regex("^([a-z0-9]+)? ?([A-Z]+)? ?([a-z0-9]+)? -> ([a-z]+)$")

fun parse(input: String): Map<String, Instruction> = input.lines()
    .mapNotNull { regex.matchEntire(it)?.destructured }
    .associate { (a, operator, b, target) ->
        target to Instruction(
            a = a.toUShortOrNull()?.asLeft() ?: if (a.isNotEmpty()) a.asRight() else null,
            operator = Operator.from(operator),
            b = b.toUShortOrNull()?.asLeft() ?: if (b.isNotEmpty()) b.asRight() else null,
        )
    }

private val memo = mutableMapOf<String, UShort>()
fun Map<String, Instruction>.getSignal(wire: String): UShort = memo.getOrPut(wire) {
    get(wire)!!.run {
        val a = a?.fold(::identity, ::getSignal)
        val b = b?.fold(::identity, ::getSignal)

        when {
            operator == null -> a!!
            a == null -> b!!.inv()
            else -> operator.invoke(a, b!!)
        }
    }
}

fun main() {
    val input = getInput(2015, 7)
    val circuit = parse(input)
    println("Wire A is provided with signal ${circuit.getSignal("a")}")
}