package nl.pindab0ter.common.helpers

import kotlin.test.assertEquals

/**
 * Asserts that all [pair]s are equal.
 *
 * @param pair The [pair]s to compare. The first element is the actual value, the second element is the expected value.
 */
fun <T> assertAllEquals(vararg pair: Pair<T, T>) {
    pair.forEach { (actual, expected) ->
        assertEquals(expected, actual)
    }
}
