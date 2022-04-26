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
        apply.reduce(Boolean::and)
    }

// atLeast('S', 1)(Vec5('S', 'L', 'A', 'T', 'E')) == true
// atLeast('S', 2)(Vec5('S', 'L', 'A', 'T', 'E')) == false
// atLeast('S', 1)(Vec5('P', 'L', 'A', 'T', 'E')) == false
fun atLeast(char: Char, amount: Int): (Vec5<Char>) -> Boolean =
    { word ->
        word.asList().count { it == char } >= amount
    }

fun lessThan(char: Char, amount: Int): (Vec5<Char>) -> Boolean = { word ->
    word.asList().count { it == char } < amount
}

fun checkInnerAmountConstraint(cell: Cell, clueWord: Vec5<Char>):
        (Vec5<Char>) -> Boolean = TODO()

fun checkAmountConstraint(clue: Vec5<Cell>): (Vec5<Char>) -> Boolean = TODO()

// utility method
operator fun <A> ((A) -> Boolean).plus(other: (A) -> Boolean): (A) -> Boolean =
    { this(it) && other(it) }

fun yellow(char: Char) = Cell(Color.YELLOW, char)
fun grey(char: Char) = Cell(Color.GREY, char)
fun green(char: Char) = Cell(Color.GREEN, char)


typealias Clue = Vec5<Cell>

val makeRuleFromClue: (clue: Clue) -> (Vec5<Char>) -> Boolean =
    ::checkCellConstraintsForWord

fun makeRulesFromClues(clues: List<Clue>): (Vec5<Char>) -> Boolean = clues
    .map(makeRuleFromClue)
    .reduce { a, b -> a.plus(b) }

val clues: List<Clue> = listOf(
    Vec5(yellow('S'), grey('L'), grey('A'), grey('T'), yellow('E')),
)

fun main(args: Array<String>) {
    val words: List<Vec5<Char>> = File("words.txt")
        .readLines()
        .map(String::uppercase)
        .map {
            Vec5(it[0], it[1], it[2], it[3], it[4])
        }


    val rules: (Vec5<Char>) -> Boolean = makeRulesFromClues(clues)
    val checkedWords: List<Vec5<Char>> = words.filter(rules)
    val wordsToPrint: List<String> = checkedWords
        .shuffled()
        .take(5)
        .map {
            it.map(Char::toString).reduce(String::plus)
        }
    for (word in wordsToPrint) {
        println(word)
    }
    println("""words left: ${checkedWords.count()}""")
}
