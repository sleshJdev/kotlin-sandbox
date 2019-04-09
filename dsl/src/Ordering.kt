class OrderBy(
    val expr: Expression
) : Expression() {
    private var asc: Boolean = true

    fun asc() {
        this.asc = true
    }

    fun desc() {
        this.asc = false
    }

    override val sql
        get() = "${expr.sql} ${if (asc) "asc" else "desc"}"
}

class Ordering : Expression() {
    private val ordering = mutableListOf<Expression>()

    fun col(name: String): OrderBy {
        val orderBy = OrderBy(Column(name))
        ordering += orderBy
        return orderBy
    }

    override val sql
        get() = "order by ${ordering.map { it.sql }.joinToString()}"
}