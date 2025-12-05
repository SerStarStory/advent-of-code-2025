import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        val ranges = input.takeWhile { it.isNotBlank() }
            .map { it.split("-").let { (a, b) -> a.toLong()..b.toLong() } }
        return input.takeLastWhile { it.isNotBlank() }.map { it.toLong() }
            .count { num -> ranges.any { num in it } }
    }

    fun part2(input: List<String>) = input.takeWhile { it.isNotBlank() }
        .map { it.split("-").let { (a, b) -> a.toLong()..b.toLong() } }
        .sortedBy { it.first }
        .fold(mutableListOf<LongRange>()) { acc, r -> acc.apply {
            val i = acc.indexOfFirst { r.first in it || r.last in it }
            if (i != -1) acc[i] = min(acc[i].first, r.first)..max(acc[i].last, r.last) else acc += r
        } }.sumOf { it.last - it.first + 1 }

    val input = readInput("day5")
    part1(input).println()
    part2(input).println()
}