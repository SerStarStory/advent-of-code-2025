fun main() {
    fun part1(input: List<Int>) = input
        .runningFold(50) { d, x -> (d + x).mod(100) }
        .count { it == 0 }

    fun part2(input: List<Int>) = input
        .fold(50 to 0) { (dial, result), amount ->
            (dial + amount).mod(100) to (result + when {
                amount > 0 -> (dial + amount) / 100
                else -> {
                    if (-amount >= dial) (-amount - dial) / 100 + (if (dial == 0) 0 else 1) else 0
                }
            })
        }.second

    val input = readInput("day1")
        .map { it.substring(1).toInt() * if(it[0] == 'L') -1 else 1}
    part1(input).println()
    part2(input).println()
}