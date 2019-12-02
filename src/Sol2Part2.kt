import java.io.File

class Sol2Part2(private val target: Int) {

    fun processFile(filepath: String): Int {
        val input = File(filepath).readLines()[0]
        val opCodes = input.split(",").map { n -> n.toInt() }
        for (n in 0..99) {
            for (v in 0..99) {
                if (runInstructions(opCodes, n, v) == target) {
                    return 100 * n + v
                }
            }
        }
        return -1
    }

    private fun runInstructions(opCodes: List<Int>, n: Int, v: Int): Int {
        val codes = opCodes.toMutableList()
        codes[1] = n
        codes[2] = v
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
    println(Sol2Part2(19690720).processFile(inputFile))
}
