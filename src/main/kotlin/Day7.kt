import java.util.LinkedList

fun main() {
    fun part1(input: List<CharArray>): Int {
        var res = 0
        fun dfs(y: Int, x: Int) {
            if (y >= input.size || x !in input[0].indices) return
            (y until input.size).forEach { cy ->
                when (input[cy][x]) {
                    '0' -> return
                    '^' -> {
                        input[cy][x] = '0'
                        res++
                        dfs(cy, x - 1)
                        dfs(cy, x + 1)
                        return
                    }
                }
            }
        }
        dfs(1, input.first().indexOf('S'))
        return res
    }

    fun part2(input: List<String>): Long {
        val i = input.map { it.map { c -> when (c) { '^' -> -2L; 'S' -> -1L; else -> 0L } }.toMutableList() }
        val visited = mutableSetOf<Pair<Int, Int>>()
        val queue = LinkedList<Pair<Int, Int>>()
        val startX = i.first().indexOf(-1)
        queue += 1 to startX
        i[1][startX] = 1

        fun sumAt(y: Int, x: Int, v: Long) {
            i.getOrNull(y)?.getOrNull(x)?.takeIf { it >= 0 }?.let { i[y][x] += v }
        }

        while (queue.isNotEmpty()) {
            val (y, x) = queue.poll().takeIf { visited.add(it) } ?: continue
            if (x >= i[0].size || y + 1 >= i.size) continue
            val cy = y + 1
            when (i[cy][x]) {
                -2L -> {
                    listOf(x - 1, x + 1).forEach { nx ->
                        queue += cy to nx
                        sumAt(cy, nx, i[y][x])
                    }
                }
                in 0L..Long.MAX_VALUE -> {
                    i[cy][x] += i[y][x]
                    queue += cy to x
                }
            }
        }
        return i.last().sum()
    }

    val input = readInput("day7")
    part1(input.map { it.toCharArray() }).println()
    part2(input).println()
}