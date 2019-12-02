import java.io.File

class Sol2 {
    fun processFile(filepath: String): Int {
        val input = File(filepath).readLines()[0]
        val opCodes = input.split(",").map { n -> n.toInt() }
        return runInstructions(opCodes)
    }

    private fun runInstructions(opCodes: List<Int>): Int {
        val codes = opCodes.toMutableList()
        codes[1] = 12
        codes[2] = 2
        var i = 0
        while (true) {
            val op = codes[i]
            if (op == 99) {
                return codes[0]
            }
            val adr1 = codes[i + 1]
            val adr2 = codes[i + 2]
            val adr3 = codes[i + 3]
            val arg1 = codes[adr1]
            val arg2 = codes[adr2]
            when (op) {
                1 -> codes[adr3] = arg1 + arg2
                2 -> codes[adr3] = arg1 * arg2
                else -> throw Exception("Unexpected op code $op at index $i")
            }
            i += 4
        }
    }
}

fun main() {
    val inputFile = "input/2.txt"
    println(Sol2().processFile(inputFile))
}
