import java.io.File

val inputFile = "input1.txt"
val testFile = "input1.tst"

fun processFile(filepath: String): Int {
    return File(filepath).useLines {
        it.toList().map { line -> getFuel(line) }.sum()
    }
}

fun getFuel(line: String): Int {
    return line.toInt() / 3 - 2
}

assert (processFile(testFile) == 33583)
println(processFile(inputFile))
