package select

import grouping.Grouping

class Select {
    private val columns: MutableList<Column> = mutableListOf()
    var grouping: Grouping? = null

    fun col(name: String): Column {
        val col = Column(name)
        columns += col
        return col
    }

    override fun toString(): String = "select ${columns.joinToString()}"
}

infix fun Select.from(init: From.() -> Unit): Query {
    val from = From()
    from.init()
    return Query(this, from)
}

fun select(init: Select.() -> Unit): Select {
    val select = Select()
    select.init()
    return select
}