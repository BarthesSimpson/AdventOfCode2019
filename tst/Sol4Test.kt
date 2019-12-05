import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Sol4Test {
    private final val sol4 = Sol4()
    @Test
    fun testFindPossPass() {
        // happy path
        assertEquals(2, sol4.findPossPass(111111, 111113).size)
        // not monotonically increasing
        assertEquals(0, sol4.findPossPass(222221, 222222).size)
        // no double
        assertEquals(0, sol4.findPossPass(123456, 123457).size)
    }

    @Test
    fun testFindPossPassExact() {
        // happy path
        assertEquals(1, sol4.findPossPass(123445, 123446, true).size)
        // not exact double
        assertEquals(0, sol4.findPossPass(111111, 111113, true).size)
    }
}