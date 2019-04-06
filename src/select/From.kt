package select

class From {
    var table: String? = null

    override fun toString(): String = table?.prependIndent("from ") ?: ""
}