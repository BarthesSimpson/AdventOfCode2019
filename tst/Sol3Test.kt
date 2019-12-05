import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Sol3Test {

    private val sol3 = Sol3()
    private val sol3p2 = Sol3Part2()
    private val testInputA = "input/3a.tst"
    private val testInputB = "input/3.tst"

    @Test
    fun testProcessFileA() {
//        assertEquals(6, sol3.processFile(testInputA))
        assertEquals(30, sol3p2.processFile(testInputA))
    }

    @Test
    fun testProcessFile() {
        assertEquals(159, sol3.processFile(testInputB))
        assertEquals(610, sol3p2.processFile(testInputB))
    }
}