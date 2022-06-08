import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun checkCellConstraintForGrey () {
        assertEquals(false, grey('A').checkCellConstraint()('A'))
        assertEquals(true, grey('A').checkCellConstraint()('C'))
    }

    @Test
    fun checkCellConstraintForGreen () {
        assertEquals(true, green('A').checkCellConstraint()('A'))
        assertEquals(false, green('A').checkCellConstraint()('C'))
    }
}