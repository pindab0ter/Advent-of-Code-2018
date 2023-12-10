package nl.pindab0ter.common

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Collections")
class CollectionsKtTest {

    @Nested
    inner class Iterable {

        @Nested
        @DisplayName("allElementsEqual")
        inner class AllElementsEqual {
            @Test
            fun `Lists of integers`() = assertAllEquals(
                true to listOf(1, 1).allElementsEqual(),
                false to listOf(0, 1).allElementsEqual(),
            )

            @Test
            fun `Lists of lists of integers`() = assertAllEquals(
                true to listOf(listOf(1), listOf(1)).allElementsEqual(),
                false to listOf(listOf(0), listOf(1)).allElementsEqual(),
            )

            @Test
            fun `Lists of pairs of integers`() = assertAllEquals(
                true to listOf(0 to 1, 0 to 1).allElementsEqual(),
                false to listOf(0 to 0, 0 to 1).allElementsEqual(),
            )
        }
    }

    @Nested
    @DisplayName("Two-dimensional iterable")
    inner class TwoDimensionalArray {
        @Nested
        @DisplayName("coordinatesOfFirst")
        inner class CoordinatesOfFirst {
            @Test
            fun `Returns the coordinates of the first match`() = assertAllEquals(
                Coordinates(0, 0) to listOf(
                    listOf(1)
                ).coordinatesOfFirst { it == 1 },
                Coordinates(1, 1) to listOf(
                    listOf(0, 0),
                    listOf(0, 1)
                ).coordinatesOfFirst { it == 1 },
                Coordinates(0, 2) to listOf(
                    listOf(0), listOf(0), listOf(1)
                ).coordinatesOfFirst { it == 1 },
                Coordinates(2, 0) to listOf(
                    listOf(0, 0, 1)
                ).coordinatesOfFirst { it == 1 },
                null to listOf(
                    listOf(0)
                ).coordinatesOfFirst { it == 1 },
            )
        }

        @Nested
        @DisplayName("find")
        inner class Find {
            @Test
            fun `Returns the first element matching the predicate, or null`() = assertAllEquals(
                1 to listOf(listOf(1)).find { it == 1 },
                1 to listOf(listOf(0, 0, 0), listOf(0, 0, 1)).find { it == 1 },
                null to listOf(listOf(0)).find { it == 1 },
            )
        }
    }

}
