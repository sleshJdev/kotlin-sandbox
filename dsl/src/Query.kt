class Query(
    val selection: Selection
    , val from: From
) {
    var where = Where()
    var grouping = Grouping()
    var having = Having()
    var ordering = Ordering()

    override fun toString(): String =
        listOf(
            selection.sql,
            from.sql,
            where.sql,
            grouping.sql,
            having.sql,
            ordering.sql
        ).filter { it.isNotBlank() }
            .joinToString("\n")
}

infix fun Query.where(init: Where.() -> Unit): Query {
    where.init()
    return this
}

infix fun Query.grouping(init: Grouping.() -> Unit): Query {
    grouping = Grouping()
    grouping.init()
    return this
}

infix fun Query.having(init: Having.() -> Unit): Query {
    having = Having()
    having.init()
    return this
}

infix fun Query.ordering(init: Ordering.() -> Unit): Query {
    ordering = Ordering()
    ordering.init()
    return this
}