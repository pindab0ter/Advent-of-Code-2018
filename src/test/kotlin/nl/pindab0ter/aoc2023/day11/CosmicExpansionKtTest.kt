package nl.pindab0ter.aoc2023.day11

import nl.pindab0ter.common.assertAllEquals
import org.junit.jupiter.api.Test

class CosmicExpansionKtTest {

    @Test
    fun `Calculate sum of distances between all galaxies`() {
        val universe = parse(
            """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent()
        )

        assertAllEquals(
            374L to universe.sumOfDistancesBetweenGalaxies(1),
            1030L to universe.sumOfDistancesBetweenGalaxies(10),
            8410L to universe.sumOfDistancesBetweenGalaxies(100),
        )
    }

}