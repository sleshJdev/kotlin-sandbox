package ordering

import select.Column

class OrderBy(
    val col: Column
) {
    private var asc: Boolean = true

    fun asc() {
        this.asc = true
    }

    fun desc() {
        this.asc = false
    }

    override fun toString(): String = "$col ${if (asc) "asc" else "desc"}"
}