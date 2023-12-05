package nl.pindab0ter.aoc2023.day05

fun main() {
    val input = ClassLoader.getSystemResource("2023/day05/input").readText()
    val almanac = parse(input)
    val lowestLocationNumber = applyMaps(almanac.seeds, almanac.maps).min()

    println("Lowest location number: $lowestLocationNumber")
}

typealias Seed = UInt
typealias LocationNumber = UInt
typealias Map = List<Range>

data class Almanac(
    val seeds: List<Seed>,
    val maps: List<Map>,
)

data class Range(
    val destinationStart: UInt,
    val sourceStart: UInt,
    val length: UInt,
)

fun parse(input: String): Almanac {
    val sections = input.split("\n\n")

    return Almanac(
        seeds = sections.first().split(":")[1].trim().split(" ").map(String::toUInt),
        maps = sections.drop(1).map { map ->
            map.split(":")[1].trim().split("\n").map { line ->
                val (destination, source, length) = line.split(" ").map(String::toUInt)
                Range(destination, source, length)
            }
        }
    )
}

fun applyMap(seeds: List<Seed>, map: Map): List<LocationNumber> = seeds.map { seed ->
    map.firstOrNull { (_, sourceStart, length) ->
        seed in sourceStart until sourceStart + length
    }?.let { (destinationStart, sourceStart, _) ->
        destinationStart + (seed - sourceStart)
    } ?: seed
}

fun applyMaps(seeds: List<Seed>, maps: List<Map>): List<LocationNumber> = maps.fold(seeds) { acc, map ->
    applyMap(acc, map)
}
