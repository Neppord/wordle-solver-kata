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

fun frequency(of:Char, inside: Vec5<Char>): Int = 
    inside.asList().count { it == of }


fun checkPerCellWordConstraint(cell: Cell, clueWord: Vec5<Char>):
        (Vec5<Char>) -> Boolean = when (cell.color) {
            Color.GREEN, Color.YELLOW -> atLeast(cell.char, frequency(cell.char, clueWord))
            Color.GREY -> lessThan(cell.char, frequency(cell.char, clueWord))
}

fun checkWordConstraint(clue: Vec5<Cell>): (Vec5<Char>) -> Boolean {
    val clueWord: Vec5<Char> = clue.map { cell -> cell.char }
    return clue.map { checkPerCellWordConstraint(it, clueWord) }
        .reduce { a, b -> a + b}
}

// utility method
operator fun <A> ((A) -> Boolean).plus(other: (A) -> Boolean): (A) -> Boolean =
    { this(it) && other(it) }

fun yellow(char: Char) = Cell(Color.YELLOW, char)
fun grey(char: Char) = Cell(Color.GREY, char)
fun green(char: Char) = Cell(Color.GREEN, char)


typealias Clue = Vec5<Cell>

fun makeRuleFromClue(clue: Clue): (Vec5<Char>) -> Boolean =
    checkCellConstraintsForWord(clue) + checkWordConstraint(clue)

fun makeRulesFromClues(clues: List<Clue>): (Vec5<Char>) -> Boolean = clues
    .map(::makeRuleFromClue)
    .reduce { a, b -> a.plus(b) }

val clues: List<Clue> = listOf(
    Vec5(grey('S'), grey('L'), yellow('A'), grey('T'), grey('E')),
    Vec5(grey('P'), green('A'), grey('G'), green('R'), yellow('I')),
    Vec5(grey('D'), green('A'), green('I'), green('R'), green('Y')),
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
