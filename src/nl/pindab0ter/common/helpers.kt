package nl.pindab0ter.common

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