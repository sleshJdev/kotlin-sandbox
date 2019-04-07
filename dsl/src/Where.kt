interface Predicate {
    fun resolve(): Boolean
}

interface PredicateExpression
    : Predicate, Expression {
}


infix fun Predicate.and(predicate: Predicate) {}

class Where : Expression {
    private val predicates = mutableListOf<Predicate>()



    override val sql
        get() = if (predicates.isNotEmpty()) "where ${predicates.map { it.sql }.joinToString(" ")}" else ""
}