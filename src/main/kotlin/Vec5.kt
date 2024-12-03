data class Vec5<A>(
    val item1: A,
    val item2: A,
    val item3: A,
    val item4: A,
    val item5: A,
) {
    constructor(item: A) : this(item, item, item, item, item)

    fun <B> map(f: (A) -> B): Vec5<B> = TODO("5")
    fun <B> apply(f: Vec5<(A) -> B>): Vec5<B> = TODO("6")

    fun asList() = listOf(item1, item2, item3, item4, item5)
    
    fun reduce(f:(A, A) -> A) = asList().reduce(f)
}