import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Sol2Test {

    private val sol2 = Sol2()
    private val testInput = "input/2.tst"

    @Test
    fun testProcessFile() {
        assertEquals(16, sol2.processFile(testInput))
    }
}