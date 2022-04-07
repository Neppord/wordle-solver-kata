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
    
    fun reduce(f:(A, A) -> A) = asList().reduce(f)
}