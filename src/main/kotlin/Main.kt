import java.io.File


typealias Clue = Vec5<Cell>
typealias Word = Vec5<Char>

// Filter rules for Wordle
fun mustContain(char: Char): (Char) -> Boolean = TODO("1")
fun mustNotContain(char: Char): (Char) -> Boolean = TODO("2")


//  
fun checkCellConstraintsForWord(clue: Clue): (Word) -> Boolean = TODO("7")

// atLeast('S', 1)(Vec5('S', 'L', 'A', 'T', 'E')) == true
// atLeast('S', 2)(Vec5('S', 'L', 'A', 'T', 'E')) == false
// atLeast('S', 1)(Vec5('P', 'L', 'A', 'T', 'E')) == false
fun atLeast(char: Char, amount: Int): (Word) -> Boolean =
    { it.asList().count { it == char } >= amount }

fun lessThan(char: Char, amount: Int): (Word) -> Boolean =
    { it.asList().count { it == char } < amount }

fun frequency(of:Char, inside: Word): Int =
    inside.asList().count { it == of }


fun checkPerCellWordConstraint(cell: Cell, clueWord: Word):
        (Word) -> Boolean = when (cell.color) {
            Color.GREEN, Color.YELLOW -> atLeast(cell.char, frequency(cell.char, clueWord))
            Color.GREY -> lessThan(cell.char, frequency(cell.char, clueWord))
}

fun checkWordConstraint(clue: Clue): (Word) -> Boolean =
    clue
        .map { checkPerCellWordConstraint(
            it,
            clue.map { cell -> cell.char }
        ) }
        .reduce { a, b -> { a(it) && b(it) } }

fun yellow(char: Char) = Cell(Color.YELLOW, char)
fun grey(char: Char) = Cell(Color.GREY, char)
fun green(char: Char) = Cell(Color.GREEN, char)


fun makeRuleFromClue(clue: Clue): (Word) -> Boolean =
    { checkCellConstraintsForWord(clue)(it) && checkWordConstraint(clue)(it) }

fun makeRulesFromClues(clues: List<Clue>): (Word) -> Boolean = clues
    .map(::makeRuleFromClue)
    .reduce { a, b -> { a(it) && b(it) } }

val clues: List<Clue> = listOf(
    Vec5(grey('S'), grey('L'), yellow('A'), grey('T'), grey('E')),
    Vec5(grey('P'), green('A'), grey('G'), green('R'), yellow('I')),
    Vec5(grey('D'), green('A'), green('I'), green('R'), green('Y')),
)

fun main() {
    val words: List<Word> = File("words.txt")
        .readLines()
        .map(String::uppercase)
        .map {
            Vec5(it[0], it[1], it[2], it[3], it[4])
        }


    val rules: (Word) -> Boolean = makeRulesFromClues(clues)
    val checkedWords: List<Word> = words.filter(rules)
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
