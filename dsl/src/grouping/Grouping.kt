package grouping

import select.Column

class Grouping {
    private val grouping: MutableList<GroupBy> = mutableListOf()

    fun col(name: String): GroupBy {
        val groupBy = GroupBy(Column(name))
        grouping += groupBy
        return groupBy
    }

    override fun toString(): String =
        if (grouping.isNotEmpty()) "group by ${grouping.joinToString()}" else ""
}