class Literal<T>(
    val value: T
) : Expression {
    override val sql: String
        get() = if (value is String) "'$value'" else value.toString()

}

class Predicate(
    val operand1: Expression,
    val operand2: Expression,
    val operation: String
) : Expression {
    override val sql: String
        get() = "${operand1.sql} $operation ${operand2.sql}"
}

class PredicateFlow<T>(
    val operand1: Expression,
    val where: Where
) {
    var operand2: Literal<T>? = null
    var operation: String? = null
}

infix fun <T> PredicateFlow<T>.eq(value: T): PredicateFlow<T> {
    val predicate = Predicate(this.operand1, Literal(value), "=")
    this.where.predicates += predicate
    return this
}

class Where : Expression {
    val predicates = mutableListOf<Predicate>()

    fun <T> col(name: String) = PredicateFlow<T>(Column(name), this)

    override val sql
        get() = if (predicates.isNotEmpty()) "where ${predicates.joinToString(" and ") { it.sql }}" else ""
}