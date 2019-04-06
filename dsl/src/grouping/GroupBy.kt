package grouping

import select.Column

class GroupBy(
    private val col: Column
) {
    override fun toString(): String = col.toString()
}