data class Cell(val color: Color, val char: Char) {
    fun checkCellConstraint() : (Char) -> Boolean =
        when (color) {
            Color.GREEN -> mustContain(char)
            Color.YELLOW, Color.GREY -> mustNotContain(char)
        }    
}