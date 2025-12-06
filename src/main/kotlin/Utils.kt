import kotlin.io.path.Path
import kotlin.io.path.readText

fun readInput(name: String) = Path("src/inputs/$name.txt").readText().lines()

fun Any?.println() = println(this)