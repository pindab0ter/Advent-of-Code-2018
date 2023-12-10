package nl.pindab0ter.common.helpers

import org.junit.jupiter.api.Test

class MathKtTest {

    @Test
    fun `Calculate the Greatest Common Divisor`() = assertAllEquals(
        gcd(18L, 48L) to 6L,
        gcd(17L, 5L) to 1L,
        gcd(27L, 18L) to 9L,
        gcd(75L, 45L) to 15L,
    )

    @Test
    fun `Calculate the Lowest Common Multiple`() = assertAllEquals(
        lcm(2L, 3L) to 6L,
        lcm(3L, 4L) to 12L,
        lcm(5L, 3L) to 15L,
        lcm(7L, 5L) to 35L,
        lcm(24L, 18L) to 72L,
        lcm(7L, 5L, 5L) to 35L,
        lcm(24L, 18L, 12L) to 72L,
    )

}