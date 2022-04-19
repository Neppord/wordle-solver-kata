import java.io.File

// Filter rules for Wordle
fun mustContain(char: Char): (Char) -> Boolean = { char == it }
fun mustNotContain(char: Char): (Char) -> Boolean = { char != it }


//  
fun checkCellConstraintsForWord(clue: Vec5<Cell>): (Vec5<Char>) -> Boolean =
    { word ->
        val cellConstraints: Vec5<(Char) -> Boolean> =
            clue.map(Cell::checkCellConstraint)
        val apply: Vec5<Boolean> = word.apply(cellConstraints)
        apply.reduce { a, b -> a && b }
    }

// atLeast('S', 1)(Vec5('S', 'L', 'A', 'T', 'E')) == true
// atLeast('S', 2)(Vec5('S', 'L', 'A', 'T', 'E')) == false
// atLeast('S', 1)(Vec5('P', 'L', 'A', 'T', 'E')) == false
fun atLeast(char: Char, amount: Int): (Vec5<Char>) -> Boolean =
    { word ->
        char in word.asList()
    }

fun lessThan(char: Char, amount: Int): (Vec5<Char>) -> Boolean = TODO()

fun checkAmountConstraint(clue: Vec5<Cell>): (Vec5<Char>) -> Boolean = TODO()

// utility method
operator fun <A> ((A) -> Boolean).plus(other: (A) -> Boolean): (A) -> Boolean =
    { this(it) && other(it) }

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
