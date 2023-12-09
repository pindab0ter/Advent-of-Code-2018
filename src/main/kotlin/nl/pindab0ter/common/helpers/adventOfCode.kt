package nl.pindab0ter.common.helpers

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.awaitUnit
import kotlinx.coroutines.runBlocking
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths


/**
 * @param year The year of the puzzle.
 * @param day The day of the puzzle.
 *
 * @return The input for the given year and day.
 */
fun getInput(year: Int, day: Int): String {
    val paddedDay = day.toString().padStart(2, '0')
    val file = Paths.get("src/main/resources/${year}/day$paddedDay/input").toFile()

    if (!file.exists()) runBlocking {
        if (!file.parentFile.exists()) Files.createDirectories(file.parentFile.toPath())

        val sessionCookie = File(".session-cookie").readText()

        Fuel
            .download("https://adventofcode.com/${year}/day/${day}/input")
            .fileDestination { response, _ ->
                if (response.statusCode == 400) throw Exception("Invalid session cookie")
                if (response.statusCode != 200) throw Exception("Unexpected status code: ${response.statusCode}, ${response.responseMessage}")

                file
            }
            // https://www.reddit.com/r/adventofcode/comments/z9dhtd/please_include_your_contact_info_in_the_useragent/
            .appendHeader(
                "User-Agent" to "https://github.com/pindab0ter/AdventOfCode",
                "From" to "hansvanluttikhuizen@me.com",
                "Cookie" to "session=$sessionCookie",
            )
            .awaitUnit()
    }

    return file.readText().trimEnd()
}
