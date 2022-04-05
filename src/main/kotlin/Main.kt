import java.io.File

enum class Color {
    GREY, YELLOW, GREEN
}

data class Cell(val color: Color, val char: Char)

data class Vec5<A>(
    val item1: A,
    val item2: A,
    val item3: A,
    val item4: A,
    val item5: A,
) {
    constructor(item: A) : this(item, item, item, item, item)

    fun <B> map(f: (A) -> B) =
        Vec5(f(item1), f(item2), f(item3), f(item4), f(item5))

    fun <B> apply(f: Vec5<(A) -> B>) = Vec5(
        f.item1(item1),
        f.item2(item2),
        f.item3(item3),
        f.item4(item4),
        f.item5(item5)
    )

    fun asList() = listOf(item1, item2, item3, item4, item5)
}

fun yellow(char: Char) = Cell(Color.YELLOW, char)
fun grey(char: Char) = Cell(Color.GREY, char)
fun green(char: Char) = Cell(Color.GREEN, char)

// Filter rules for Wordle
fun mustContain(char: Char): (Char) -> Boolean = { char == it }
fun mustNotContain(char: Char): (Char) -> Boolean = { char != it }
fun checkCellConstraintForCell(cell: Cell): (Char) -> Boolean = TODO()
fun checkCellConstraintsForWord(clue: Vec5<Cell>): (Vec5<Char>) -> Boolean = TODO()

fun atLeast(char: Char, amount: Int): (Vec5<Char>) -> Boolean = TODO()
fun lessThan(char: Char, amount: Int): (Vec5<Char>) -> Boolean = TODO()

fun checkAmountConstraint(clue: Vec5<Cell>): (Vec5<Char>) -> Boolean = TODO()

// utility method
fun <A> ((A) -> Boolean).and(other: (A) -> Boolean): (A) -> Boolean = TODO()

fun main(args: Array<String>) {
    val words:List<Vec5<Char>> = File("words.txt")
        .readLines()
        .map {
            Vec5(it[0], it[1], it[2], it[3], it[4])
        }
    val clue =
        Vec5(grey('S'), yellow('L'), yellow('A'), yellow('T'), grey('E'))
}
