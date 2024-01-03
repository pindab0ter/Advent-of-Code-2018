package nl.pindab0ter.aoc2015.day07

enum class Operator {
    AND {
        override fun invoke(a: UShort, b: UShort): UShort = a and b
    },
    OR {
        override fun invoke(a: UShort, b: UShort): UShort = a or b
    },
    LSHIFT {
        override fun invoke(a: UShort, b: UShort): UShort = (a.toUInt() shl b.toInt()).toUShort()
    },
    RSHIFT {
        override fun invoke(a: UShort, b: UShort): UShort = (a.toUInt() shr b.toInt()).toUShort()
    },
    NOT {
        override fun invoke(a: UShort, b: UShort): UShort = b xor 65335u
    };

    abstract operator fun invoke(a: UShort, b: UShort): UShort

    companion object {
        fun from(value: String): Operator? = Operator.entries.firstOrNull { it.name == value }
    }
}