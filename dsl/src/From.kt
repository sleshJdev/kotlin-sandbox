class Table(
    val name: String
)

class From : Expression() {
    private var table: Table? = null

    fun table(name: String, init: (Table.() -> Unit)? = null) {
        table = Table(name)
        init?.let { table?.apply(it) }
    }

    override val sql
        get() = table?.name?.prependIndent("from ") ?: ""
}