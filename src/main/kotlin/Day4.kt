fun main() {
    val offsets = arrayOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)

    fun check(grid: List<CharArray>, x: Int, y: Int) =
        grid[y][x] == '@' && offsets.count { (dx, dy) ->
            (y + dy) in grid.indices && (x + dx) in grid[y + dy].indices && grid[y + dy][x + dx] == '@'
        } < 4

    fun part1(grid: List<CharArray>) = grid.indices
        .sumOf { y -> grid[y].indices.count { x -> check(grid, x, y) } }

    fun part2(grid: List<CharArray>) = generateSequence {
        grid.flatMapIndexed { y, row ->
            row.indices.filter { x -> check(grid, x, y) }.map { x -> x to y }
        }.takeIf { it.isNotEmpty() }?.also { it.forEach { (x, y) -> grid[y][x] = '.' } }
    }.sumOf { it.size }

    val input = readInput("day4").map { it.toCharArray() }
    part1(input).println()
    part2(input).println()
}