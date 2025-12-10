import kotlin.math.abs

fun main() {
    fun part1(input: List<Pair<Long, Long>>) = (0 until input.size * input.size).maxOf {
        val a = input[it % input.size]
        val b = input[it / input.size]
        return@maxOf (abs(a.first - b.first) + 1) * (abs(a.second - b.second) + 1)
    }

    fun part2(input: List<Pair<Long, Long>>): Long {
        TODO()
    }


    val input = readInput("day9")
        .map { it.split(",") }
        .map { (a, b) -> a.toLong() to b.toLong() }
    part1(input).println()
    part2(input).println()
}