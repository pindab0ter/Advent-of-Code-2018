package nl.pindab0ter.common

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Represents a value of one of two possible types (a disjoint union).
 */
@OptIn(ExperimentalContracts::class)
sealed class Either<out A, out B> {
    fun isLeft(): Boolean {
        contract {
            returns(true) implies (this@Either is Left<A>)
            returns(false) implies (this@Either is Right<B>)
        }
        return this@Either is Left<A>
    }

    /**
     * Returns the unwrapped value [B] of [Either.Right] or `null` if it is [Either.Left].
     */
    fun right(): B? {
        contract {
            returns(null) implies (this@Either is Left<A>)
            returnsNotNull() implies (this@Either is Right<B>)
        }
        return fold({ null }, ::identity)
    }

    /**
     * Returns the unwrapped value [B] of [Either.Right] or `null` if it is [Either.Left].
     */
    fun rightOrNull(): B? {
        contract {
            returns(null) implies (this@Either is Left<A>)
            returnsNotNull() implies (this@Either is Right<B>)
        }
        return fold({ null }, ::identity)
    }

    /**
     * Returns the unwrapped value [A] of [Either.Left] or `null` if it is [Either.Right].
     */
    @Suppress("MoveLambdaOutsideParentheses")
    fun leftOrNull(): A? {
        contract {
            returnsNotNull() implies (this@Either is Left<A>)
            returns(null) implies (this@Either is Right<B>)
        }
        return fold(::identity, { null })
    }


    /**
     * Transform an [Either] into a value of [C].
     * Alternative to using `when` to fold an [Either] into a value [C].
     *
     * @param ifLeft transform the [Either.Left] type [A] to [C].
     * @param ifRight transform the [Either.Right] type [B] to [C].
     * @return the transformed value [C] by applying [ifLeft] or [ifRight] to [A] or [B] respectively.
     */
    inline fun <C> fold(ifLeft: (left: A) -> C, ifRight: (right: B) -> C): C {
        contract {
            callsInPlace(ifLeft, InvocationKind.AT_MOST_ONCE)
            callsInPlace(ifRight, InvocationKind.AT_MOST_ONCE)
        }
        return when (this) {
            is Right -> ifRight(value)
            is Left -> ifLeft(value)
        }
    }

    /**
     * The left side of the disjoint union, as opposed to the [Right] side.
     */
    data class Left<out A>(val value: A) : Either<A, Nothing>() {
        override fun toString(): String = "Either.Left($value)"
    }

    /**
     * The right side of the disjoint union, as opposed to the [Left] side.
     */
    data class Right<out B>(val value: B) : Either<Nothing, B>() {
        override fun toString(): String = "Either.Right($value)"
    }
}
