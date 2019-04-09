interface Predicate : Expression {
    fun resolve(): Boolean
}

class Where : Expression {
    private val predicates = mutableListOf<Predicate>()

    override val sql
        get() = if (predicates.isNotEmpty()) "where ${predicates.map { it.sql }.joinToString(" ")}" else ""
}