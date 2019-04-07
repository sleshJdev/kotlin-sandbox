class Having : Expression {
    private val columns = mutableListOf<Expression>()

    fun col(name: String): Column {
        val col = Column(name)
        columns += col
        return col
    }

    override val sql
        get() = if (columns.isNotEmpty()) "having ${columns.joinToString()}" else ""
}