import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class MainKtTest {
    @Test
    fun mustcontain() {
        assertEquals(true, mustContain('S')('S'))
        assertEquals(false, mustContain('Y')('Z'))
    }
    @Test
    fun mustNotContain() {
        assertEquals(false, mustNotContain('S')('S'))
        assertEquals(true, mustNotContain('Y')('S'))
    }

    @Test
    fun checkCellConstraintForCell() {
        val checker: (Char) -> Boolean = green('S').checkCellConstraint()
        assertEquals(true, checker('S'))
        assertEquals(false, checker('Y'))
        val yellowChecker: (Char) -> Boolean =
            yellow('S').checkCellConstraint()
        assertEquals(false, yellowChecker('S'))
        assertEquals(true, yellowChecker('Y'))
    }

    @Test
    internal fun checkCellForWord() {
        val clue = Vec5(green('S'))
        val word = Vec5('S')
        val actual = checkCellConstraintsForWord(clue)(word)
        assertEquals(true, actual)
    }

    @Test
    internal fun checkCellForWordFalse() {
        val clue = Vec5(grey('S'))
        val word = Vec5('S')
        val actual = checkCellConstraintsForWord(clue)(word)
        assertEquals(false, actual)
    }
}