package select

class Column(
    val name: String
    , private var alias: String? = null
) {

    fun alias(alias: String?): Column {
        this.alias = alias
        return this
    }

    override fun toString(): String = "$name${alias?.prependIndent(" as ") ?: ""}"
}