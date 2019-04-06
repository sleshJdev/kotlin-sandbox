package having

import select.Column

class Having {
    private val columns: MutableList<Column> = mutableListOf()
    fun col(name: String): Column {
        val col = Column(name)
        columns += col
        return col
    }

    override fun toString(): String =
        if (columns.isNotEmpty()) "select.having ${columns.joinToString()}" else ""
}