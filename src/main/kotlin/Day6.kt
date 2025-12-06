import java.math.BigInteger

fun main() {
    val regex = "\\s+".toRegex()

    fun part1(input: List<String>): BigInteger {
        val i = input.map { it.split(regex).filter { i -> i.isNotBlank() } }
        val numbers = i.dropLast(1).map { it.map { i -> i.toBigInteger() } }
        val ops = i.last()
        return ops.indices.sumOf {
            if (ops[it] == "+") numbers.sumOf { n -> n[it] } else numbers.fold(BigInteger.ONE) { p, n -> p * n[it] }
        }
    }

    fun part2(input: List<String>): BigInteger {
        val realNumbers = input.dropLast(1).map { it.split(regex).filter { i -> i.isNotBlank() } }
        val transformedNumbers = (0 until input.maxOf { it.length })
            .map { col -> (0 until input.size - 1).joinToString("") { row -> (input[row].getOrNull(col) ?: ' ').toString() } }
            .filter { it.isNotBlank() }
            .map { it.trim().toBigInteger() }
        val ops = input.last().split(regex).filter { it.isNotBlank() }
        val numbers = ops.indices
            .map { realNumbers.maxOf { n -> n[it].length } }
            .scan(0) { acc, m -> acc + m }
            .zipWithNext { start, end -> transformedNumbers.subList(start, end) }
        return ops.indices.sumOf { i ->
            if (ops[i] == "+") numbers[i].sumOf { it } else numbers[i].fold(BigInteger.ONE) { p, n -> p * n }
        }
    }

    val input = readInput("day6")
    part1(input).println()
    part2(input).println()
}