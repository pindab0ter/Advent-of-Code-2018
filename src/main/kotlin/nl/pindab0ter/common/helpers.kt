package nl.pindab0ter.common

fun <T> Iterable<T>.tail(): Iterable<T> = drop(1)
fun <T> Iterable<T>.productOf(selector: (T) -> Int): Int = fold(1) { acc, element -> acc * selector(element) }

fun <T> timing(name: String? = null, block: () -> T) {
    val start = System.currentTimeMillis()
    try {
        block.invoke()
    } finally {
        val end = System.currentTimeMillis()

        println(buildString {
            when (name) {
                null -> append("Took ")
                else -> append("$name took ")
            }
            append(end - start)
            append(" ms.")
        })
    }
}