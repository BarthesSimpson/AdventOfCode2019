import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Sol1Test {

    private val sol1 = Sol1()
    private val testFile = "input/1.tst"

    @Test
    fun testProcessFile() {
        kotlin.test.assertEquals(654 + 33583, sol1.processFile(testFile))
    }

    @Test
    fun getFuel() {
        assertEquals(2, sol1.getFuel("12"))
        assertEquals(2, sol1.getFuel("14"))
        assertEquals(654, sol1.getFuel("1969"))
        assertEquals(33583, sol1.getFuel("100756"))
    }
}