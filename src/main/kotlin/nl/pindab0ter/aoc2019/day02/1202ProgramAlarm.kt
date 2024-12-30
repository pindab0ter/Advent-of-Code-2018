package nl.pindab0ter.aoc2019.day02

import arrow.core.firstOrNone
import arrow.core.getOrElse
import nl.pindab0ter.aoc2019.day02.Instruction.*
import nl.pindab0ter.common.getInput

fun main() {
    val memory = getInput(2019, 2).parse()

    val computer = IntcodeComputer(memory)

    computer.memory[1] = 12
    computer.memory[2] = 2

    computer.run()

    println("The value at position 0 is ${computer.memory[0]}")
}

fun String.parse(): Memory = split(",").map(String::toInt).toMutableList()

typealias Memory = MutableList<Int>

class UnknownOpcodeException() : Exception()

enum class Instruction(val integer: Int) {
    ADD(1),
    MULTIPLY(2),
    EXIT(99);

    companion object {
        fun fromOpcode(opcode: Int) = entries
            .firstOrNone { it.integer == opcode }
            .getOrElse { throw UnknownOpcodeException() }
    }
}

class IntcodeComputer(val memory: Memory) {
    var currentPosition = 0
    private val instruction: Instruction
        get() = Instruction.fromOpcode(memory[currentPosition])
    private val leftOperandPosition: Int
        get() = memory[currentPosition + 1]
    private val leftOperandValue: Int
        get() = memory[leftOperandPosition]
    private val rightOperandPosition: Int
        get() = memory[currentPosition + 2]
    private val rightOperandValue: Int
        get() = memory[rightOperandPosition]
    private val resultPosition: Int
        get() = memory[currentPosition + 3]

    private fun moveToNextInstruction() {
        currentPosition += 4
    }

    private fun executeInstruction() {
        println(this)
        when (instruction) {
            ADD -> memory[resultPosition] = leftOperandValue + rightOperandValue
            MULTIPLY -> memory[resultPosition] = leftOperandValue * rightOperandValue
            EXIT -> return
        }
    }

    fun run(): Memory {
        while (instruction != EXIT) {
            executeInstruction()
            moveToNextInstruction()
        }
        return memory
    }

    override fun toString(): String {
        return "IntcodeComputer(memory=$memory, currentPosition=$currentPosition, instruction=$instruction, leftOperandPosition=$leftOperandPosition, leftOperandValue=$leftOperandValue, rightOperandPosition=$rightOperandPosition, rightOperandValue=$rightOperandValue, resultPosition=$resultPosition)"
    }
}
