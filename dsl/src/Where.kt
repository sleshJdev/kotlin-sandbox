class And (
    val a: Predicate,
    val b: Predicate
)

class Predicate(
    val expr: Expression
) : Expression() {

    override val sql
        get() = expr.sql
}

infix fun Predicate.and(predicate: Predicate) {}

class Where : Expression() {
    private val predicates = mutableListOf<Predicate>()

    fun predicate(name: String): Predicate {
        val predicate = Predicate(Column(name))
        predicates += predicate
        return predicate
    }

    fun and(init: And.() -> Unit) {

    }

    override val sql
        get() = if (predicates.isNotEmpty()) "where ${predicates.map { it.sql }.joinToString(" ")}" else ""
}