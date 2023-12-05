package nl.pindab0ter.aoc2023.day05

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import nl.pindab0ter.common.timing

fun main() {
    val input = ClassLoader.getSystemResource("2023/day05/input").readText()
    val almanac = Almanac(input)

    val lowestLocationNumberPartOne = applyMaps(almanac.partOneSeeds, almanac.maps).min()
    println("Lowest location number, part one: $lowestLocationNumberPartOne")

    // Fails due to Java heap space error
    timing("Reverse search") {
        val lowestLocationNumberPartTwo = findLowestSeed(almanac.partTwoSeeds.toSet(), almanac.maps)
        println("Lowest location number, part two: $lowestLocationNumberPartTwo")
    }
}


data class Almanac(
    val partOneSeeds: Sequence<ULong>,
    val partTwoSeeds: Sequence<ULong>,
    val maps: List<Map>,
) {

    companion object {
        operator fun invoke(input: String): Almanac {
            val sections = input.split("\n\n")

            val partOneSeeds = sections
                .first()
                .split(":")[1].trim()
                .splitToSequence(" ")
                .map(String::toULong)

            val partTwoSeeds = partOneSeeds
                .chunked(2) { (start, length) -> (start until start + length) }
                .flatten()

            val maps = sections.drop(1).map { map ->
                map.split(":")[1].trim().splitToSequence("\n").map { line ->
                    val (destination, source, length) = line.split(" ").map(String::toULong)
                    Range(destination, source, length)
                }.toList()
            }

            return Almanac(partOneSeeds, partTwoSeeds, maps)
        }
    }
}

typealias Map = List<Range>

data class Range(
    val destinationStart: ULong,
    val sourceStart: ULong,
    val length: ULong,
)

//////////////
// Part one //
//////////////

fun applyMap(seed: ULong, map: Map): ULong = map.firstOrNull { (_, sourceStart, length) ->
    seed >= sourceStart && seed < sourceStart + length
}?.let { (destinationStart, sourceStart, _) ->
    destinationStart + (seed - sourceStart)
} ?: seed

fun applyMaps(seeds: Sequence<ULong>, maps: List<Map>): List<ULong> = runBlocking {
    seeds.asFlow()
        .map { seed -> maps.fold(seed) { acc, map -> applyMap(acc, map) } }
        .toList()
}

//////////////
// Part two //
//////////////

fun applyMapReverse(location: ULong, map: Map): ULong = map
    .firstOrNull { (destinationStart, _, length) ->
        location >= destinationStart && location < destinationStart + length
    }?.let { (destinationStart, sourceStart, _) ->
        (sourceStart - destinationStart) + location
    } ?: location


tailrec fun findLowestSeed(seeds: Set<ULong>, maps: List<Map>, location: ULong = 1u): ULong {
    val seed = maps.reversed().fold(location) { acc, map ->
        applyMapReverse(acc, map)
    }

    if (seeds.contains(seed)) return location

    return findLowestSeed(seeds, maps, location + 1u)
}