fun main() {
    fun part1(input: List<String>) = input.sumOf { s ->
        val i = (0 until s.length - 1).maxBy { s[it] }
        ("" + s[i] + (s.drop(i + 1).max())).toInt()
    }

    fun part2(input: List<String>) = input.sumOf { line ->
        (0..11).fold(0 to "") { (start, result), i ->
            val md = line.substring(start, line.length - (11 - i)).max()
            (line.indexOf(md, start) + 1) to (result + md)
        }.second.toLong()
    }

    val input = readInput("day3")
    part1(input).println()
    part2(input).println()
}