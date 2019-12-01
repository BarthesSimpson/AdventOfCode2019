import java.io.File

fun main() {
    val inputFile = "input/1.txt"
    val testFile = "input/1.tst"

    fun processFile(filepath: String): Int {
        return File(filepath).useLines {
            it.toList().map { line -> getFuel(line) }.sum()
        }
    }
    assert(processFile(testFile) == 33583)
    println(processFile(inputFile))
}

fun getFuel(line: String): Int {
    return line.toInt() / 3 - 2
}

