import java.util.PriorityQueue

typealias IntTriple = Triple<Int, Int, Int>
typealias IntTriplePair = Pair<IntTriple, IntTriple>

fun main() {
    fun getQueue(input: List<IntTriple>): PriorityQueue<Pair<Float, IntTriplePair>> {
        val pq = PriorityQueue<Pair<Float, IntTriplePair>>(compareBy { it.first })
        for (a in 0 until (input.size - 1)) {
            for (b in (a + 1) until input.size) {
                val at = input[a]
                val bt = input[b]
                val (ax, ay, az) = at
                val (bx, by, bz) = bt
                val dx = (ax - bx).toFloat()
                val dy = (ay - by).toFloat()
                val dz = (az - bz).toFloat()
                val sqDist = dx * dx + dy * dy + dz * dz
                pq += sqDist to (at to bt)
            }
        }
        return pq
    }

    fun process(pq: PriorityQueue<Pair<Float, IntTriplePair>>, clusters: MutableList<Set<IntTriple>>): IntTriplePair? {
        if (pq.isEmpty()) return null
        val (at, bt) = pq.poll().second
        val list = clusters.filter { at in it || bt in it }
        clusters.removeAll(list)
        clusters += list.flatten().toSet()
        return at to bt
    }

    fun part1(input: List<IntTriple>): Int {
        val pq = getQueue(input)
        val clusters = mutableListOf<Set<IntTriple>>()
        input.forEach { clusters += setOf(it) }
        for (i in 0..999) { // 1000 pairs
            process(pq, clusters) ?: break
        }
        return clusters.map { it.size }.sortedByDescending { it }.take(3).reduce(Int::times)
    }

    fun part2(input: List<IntTriple>): Long {
        val pq = getQueue(input)
        val clusters = mutableListOf<Set<IntTriple>>()
        input.forEach { clusters += setOf(it) }
        while (true) {
            val p = process(pq, clusters) ?: break
            if (clusters.size == 1) {
                return p.first.first.toLong() * p.second.first
            }
        }
        throw IllegalStateException()
    }

    val input = readInput("day8")
        .map { it.split(",") }
        .map { (a, b, c) -> Triple(a.toInt(), b.toInt(), c.toInt()) }
    part1(input).println()
    part2(input).println()
}