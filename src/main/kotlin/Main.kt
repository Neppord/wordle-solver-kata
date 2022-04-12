import java.io.File

// Filter rules for Wordle
fun mustContain(char: Char): (Char) -> Boolean = { char == it }
fun mustNotContain(char: Char): (Char) -> Boolean = { char != it }

fun checkCellConstraintsForWord(clue: Vec5<Cell>): (Vec5<Char>) -> Boolean = 
    { val cell = clue.item1
        val char = it.item1
        cell.checkCellConstraint()(char) }
fun atLeast(char: Char, amount: Int): (Vec5<Char>) -> Boolean = TODO()
fun lessThan(char: Char, amount: Int): (Vec5<Char>) -> Boolean = TODO()

fun checkAmountConstraint(clue: Vec5<Cell>): (Vec5<Char>) -> Boolean = TODO()

// utility method
fun <A> ((A) -> Boolean).and(other: (A) -> Boolean): (A) -> Boolean = TODO()

fun yellow(char: Char) = Cell(Color.YELLOW, char)
fun grey(char: Char) = Cell(Color.GREY, char)
fun green(char: Char) = Cell(Color.GREEN, char)

fun main(args: Array<String>) {
    val words: List<Vec5<Char>> = File("words.txt")
        .readLines()
        .map {
            Vec5(it[0], it[1], it[2], it[3], it[4])
        }
    val clue =
        Vec5(grey('S'), yellow('L'), yellow('A'), yellow('T'), grey('E'))
}
