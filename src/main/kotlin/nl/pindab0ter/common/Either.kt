package nl.pindab0ter.common

/**
 * Represents a value of one of two possible types (a disjoint union).
 */
data class Either<A, B>(val left: A?, val right: B?) {
    init {
        require((left == null) xor (right == null)) { "Either must contain either a left or a right value" }
    }
}
