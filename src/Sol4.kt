class Sol4 {
    fun findPossPass(min: Int, max: Int, exact: Boolean = false): List<Int> {
        // brutal force!
        return (min until max).filter {
            meetsCriteria(it, exact)
        }
    }

    fun meetsCriteria(n: Int, exact: Boolean): Boolean {
        if (!exact && !containsPair(n)) return false
        if (exact && !containsExactPair(n)) return false
        if (!isMonotonicallyIncreasing(n)) return false
        return true
    }

    fun containsExactPair(n: Int): Boolean {
        var prev = n % 10
        var m = n / 10
        var cnt = 1
        while (m > 0) {
            val curr = m % 10
            if (curr != prev) {
                if (cnt == 2) return true
                cnt = 1
            } else {
                cnt += 1
            }
            prev = curr
            m /= 10
        }
        return cnt == 2
    }

    fun containsPair(n: Int): Boolean {
        var prev = n % 10
        var m = n / 10
        while (m > 0) {
            val curr = m % 10
            if (curr == prev) return true
            prev = curr
            m /= 10
        }
        return false
    }

    fun isMonotonicallyIncreasing(n: Int): Boolean {
        var prev = n % 10
        var m = n / 10

        while (m > 0) {
            val curr = m % 10
            if (curr > prev) return false
            prev = curr
            m /= 10
        }
        return true
    }
}

fun main() {
    val inputMin = 272091
    val inputMax = 815432
    println(Sol4().findPossPass(inputMin, inputMax).size)
    println(Sol4().findPossPass(inputMin, inputMax, true).size)
}
