data class Cell(val color: Color, val char: Char) {
    fun checkCellConstraint() : (Char) -> Boolean =
        when (color) {
            Color.GREEN -> TODO("3")
            Color.YELLOW, Color.GREY -> TODO("4")
        }    
}