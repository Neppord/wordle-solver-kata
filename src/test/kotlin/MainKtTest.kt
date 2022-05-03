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

    @Test
    internal fun checkCellForWordDifferentCells() {
        val clue =
            Vec5(green('S'), green('Y'), green('S'), green('S'), green('S'))
        val word = Vec5('S')
        val actual = checkCellConstraintsForWord(clue)(word)
        assertEquals(false, actual)
    }

    @Test
    internal fun checkAtleastACharacterPresent() {
        val word = Vec5('S')
        val actual = atLeast('S', 1)(word)
        assertEquals(true, actual)
    }

    @Test
    fun checkCharacterNotPresent() {
        val word = Vec5('A')
        val actual = atLeast('S', 1)(word)
        assertEquals(false, actual)
    }

    @Test
    fun checkCharacterNotPresentAmount() {
        val word = Vec5('S', 'A', 'A', 'A', 'A')
        val actual = atLeast('S', 1)(word)
        assertEquals(true, actual)
    }

    @Test
    fun checkCharacterNotPresentAmount2() {
        val word = Vec5('S', 'A', 'A', 'A', 'A')
        val actual = atLeast('S', 2)(word)
        assertEquals(false, actual)
    }

    @Test
    fun checkCharacterLessThan1() {
        val word = Vec5('S')
        val actual = lessThan('A', 1)(word)
        assertEquals(true, actual)
    }

    @Test
    fun checkCharacterNotLessThan1() {
        val word = Vec5('S')
        val actual = lessThan('S', 1)(word)
        assertEquals(false, actual)
    }

    @Test
    fun checkCharacterLessThan2() {
        val word = Vec5('S', 'A', 'A', 'A', 'A')
        val actual = lessThan('S', 2)(word)
        assertEquals(true, actual)
    }

    @Test
    fun checkFrequency() {
        assertEquals(0, frequency('R', Vec5('S')))
        assertEquals(1, frequency('S', Vec5('S', 'R', 'R', 'R', 'R')))
        assertEquals(2, frequency('S', Vec5('R', 'S', 'S', 'R', 'R')))
        assertEquals(5, frequency('S', Vec5('S')))
    }

    @Test
    internal fun perCellWordConstraintPassAllSss() {
        val greenS = green('S')
        val word: Vec5<Char> = Vec5('S')
        val check: (Vec5<Char>) -> Boolean =
            checkPerCellWordConstraint(greenS, word)
        assertEquals(true, check(word))
    }
    @Test
    internal fun perCellWordConstraintFailToFewSsss() {
        val greenS = green('S')
        val clueWord: Vec5<Char> = Vec5('S')
        val guessWord: Vec5<Char> = Vec5('S', 'R', 'R', 'R', 'R')
        val check: (Vec5<Char>) -> Boolean =
            checkPerCellWordConstraint(greenS, clueWord)
        assertEquals(false, check(guessWord))
    }  
    @Test
    internal fun perCellWordConstraintDifferentWordsButtPasses() {
        val greenS = green('S')
        val clueWord: Vec5<Char> = Vec5('S', 'W', 'R', 'R', 'R')
        val guessWord: Vec5<Char> = Vec5('S', 'R', 'R', 'R', 'R')
        val check: (Vec5<Char>) -> Boolean =
            checkPerCellWordConstraint(greenS, clueWord)
        assertEquals(true, check(guessWord))
    }
    @Test
    internal fun perCellWordConstraintGrayCell() {
        val greenS = grey('S')
        val clueWord: Vec5<Char> = Vec5('S', 'W', 'R', 'R', 'R')
        val guessWord: Vec5<Char> = Vec5('S', 'R', 'R', 'R', 'R')
        val check: (Vec5<Char>) -> Boolean =
            checkPerCellWordConstraint(greenS, clueWord)
        assertEquals(false, check(guessWord))
    }
}