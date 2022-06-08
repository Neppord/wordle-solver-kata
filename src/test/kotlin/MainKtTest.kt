import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class MainKtTest {
    @Test
    fun mustContain() {
        assertEquals(true, mustContain('S')('S'))
        assertEquals(false, mustContain('Y')('Z'))
    }
}