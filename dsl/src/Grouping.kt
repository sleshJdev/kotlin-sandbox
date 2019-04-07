class GroupBy(
    private val expr: Expression
) : Expression {
    override val sql
        get() = expr.sql
}

class Grouping : Expression {
    private val grouping = mutableListOf<Expression>()

    fun col(name: String): GroupBy {
        val groupBy = GroupBy(Column(name))
        grouping += groupBy
        return groupBy
    }

    override val sql
        get() = if (grouping.isNotEmpty()) "group by ${grouping.map { it.sql }.joinToString()}" else ""
}