import java.io.File

// Filter rules for Wordle
fun mustContain(char: Char): (Char) -> Boolean = 
    TODO("Start Here after fixing the TODO in Main")
fun mustNotContain(char: Char): (Char) -> Boolean = TODO()

fun checkCellConstraintsForWord(clue: Vec5<Cell>): (Vec5<Char>) -> Boolean =
    TODO()

// atLeast('S', 1)(Vec5('S', 'L', 'A', 'T', 'E')) == true
// atLeast('S', 2)(Vec5('S', 'L', 'A', 'T', 'E')) == false
// atLeast('S', 1)(Vec5('P', 'L', 'A', 'T', 'E')) == false
fun atLeast(char: Char, amount: Int): (Vec5<Char>) -> Boolean = TODO()

fun lessThan(char: Char, amount: Int): (Vec5<Char>) -> Boolean = TODO()

fun frequency(of:Char, inside: Vec5<Char>): Int = TODO()


fun checkPerCellWordConstraint(cell: Cell, clueWord: Vec5<Char>):
        (Vec5<Char>) -> Boolean = TODO()

fun getClueWord(clue: Vec5<Cell>): Vec5<Char> =
    TODO()

fun checkWordConstraint(clue: Vec5<Cell>): (Vec5<Char>) -> Boolean =
    TODO() 

// utility method
operator fun <A> ((A) -> Boolean).plus(other: (A) -> Boolean): (A) -> Boolean =
    { this(it) && other(it) }

fun yellow(char: Char) = Cell(Color.YELLOW, char)
fun grey(char: Char) = Cell(Color.GREY, char)
fun green(char: Char) = Cell(Color.GREEN, char)


typealias Clue = Vec5<Cell>

fun makeRuleFromClue(clue: Clue): (Vec5<Char>) -> Boolean =
    TODO()

fun makeRulesFromClues(clues: List<Clue>): (Vec5<Char>) -> Boolean =
    TODO()

val clues: List<Clue> = listOf(
    Vec5(grey('S'), grey('L'), yellow('A'), grey('T'), grey('E'))
)

fun main(args: Array<String>) {
    val words: List<Vec5<Char>> = File("words.txt")
        .readLines()
        .map(String::uppercase)
        .map { TODO("Start Here") }


    val rules: (Vec5<Char>) -> Boolean = makeRulesFromClues(clues)
    val checkedWords: List<Vec5<Char>> = words.filter(rules)
    val wordsToPrint: List<String> = checkedWords
        .shuffled()
        .take(25)
        .map {
            it.map(Char::toString).reduce(String::plus)
        }
    for (word in wordsToPrint) {
        println(word)
    }
    println("""words left: ${checkedWords.count()}""")
}
