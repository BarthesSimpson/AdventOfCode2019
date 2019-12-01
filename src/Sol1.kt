import java.io.File

class Sol1 {
    fun processFile(filepath: String): Int {
        return File(filepath).useLines {
            it.toList().map { line -> getFuel(line) }.sum()
        }
    }

    fun getFuel(line: String): Int {
        return line.toInt() / 3 - 2
    }
}

fun main() {
    val inputFile = "input/1.txt"
    println(Sol1().processFile(inputFile))
}
