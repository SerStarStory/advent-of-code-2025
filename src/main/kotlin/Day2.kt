fun main() {
    fun part1(input: List<Long>) = input
        .filter {
            val s = it.toString()
            val l = s.length
            if (l % 2 == 1) {
                return@filter false
            }
            return@filter s.take(l / 2) == s.takeLast(l / 2)
        }.sum()

    fun part2(input: List<Long>) = input
        .filter {
            val s = it.toString()
            val l = s.length
            (1..l / 2).any { patternLen ->
                if (l % patternLen != 0) return@any false
                val pattern = s.take(patternLen)
                s == pattern.repeat(l / patternLen)
            }
        }.sum()

    val input = readInput("day2")
        .flatMap { it.split(",") }
        .map { p -> p.split("-").let { it[0].toLong() to it[1].toLong() } }
        .flatMap { LongRange(it.first, it.second) }
    part1(input).println()
    part2(input).println()
}