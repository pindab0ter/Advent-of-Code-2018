package nl.pindab0ter.common

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

@DisplayName("Either")
class EitherTest {

    @Test
    fun `Left or right can be not null`() {
        assertDoesNotThrow { Either(1, null) }
        assertDoesNotThrow { Either(null, 1) }
        assertThrows<IllegalArgumentException> { Either(1, 1) }
        assertThrows<IllegalArgumentException> { Either(null, null) }
    }

}