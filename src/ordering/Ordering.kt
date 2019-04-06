package ordering

import select.Column

class Ordering {
    private val ordering: MutableList<OrderBy> = mutableListOf()
    fun col(name: String): OrderBy {
        val orderBy = OrderBy(Column(name))
        ordering += orderBy
        return orderBy
    }

    override fun toString(): String = "order by ${ordering.joinToString()}"
}