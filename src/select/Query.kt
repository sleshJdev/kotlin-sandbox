package select

import grouping.Grouping
import having.Having
import ordering.Ordering
import where.Where

class Query(
    val select: Select
    , val from: From
) {
    var where = Where()
    var grouping = Grouping()
    var having = Having()
    var ordering = Ordering()

    init {
        select.grouping = grouping
    }

    override fun toString(): String = listOf(
        select,
        from,
        where,
        grouping,
        having,
        ordering
    ).map { it.toString() }
        .filter { it.isNotBlank() }
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