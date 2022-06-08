data class Cell(val color: Color, val char: Char) {
    fun checkCellConstraint() : (Char) -> Boolean = when (color)
    {
        Color.GREY, Color.YELLOW -> mustNotContain(char)
        Color.GREEN -> mustContain(char)
    }
}