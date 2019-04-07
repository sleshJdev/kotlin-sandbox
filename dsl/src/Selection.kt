abstract class Alias : Expression {
    var alias: String? = null
    fun alias(alias: String?) {
        this.alias = alias
    }

    abstract val name: String

    override val sql
        get() = "$name${alias?.prependIndent(" as ") ?: ""}"
}

enum class Agg {
    avg, count, sum
}

class Avg(expr: Expression) : Alias() {
    override val name: String = "avg(${expr.sql})"
}

class Count(expr: Expression) : Alias() {
    override val name: String = "count(${expr.sql})"
}

class Sum(expr: Expression) : Alias() {
    override val name: String = "sum(${expr.sql})"
}

class Selection : Expression {
    private val selection = mutableListOf<Expression>()

    fun col(name: String): Alias {
        val col = Column(name)
        selection += col
        return col
    }

    fun avg(name: String): Alias {
        return agg(name, Agg.avg)
    }

    fun count(name: String): Alias {
        return agg(name, Agg.count)
    }

    fun sum(name: String): Alias {
        return agg(name, Agg.sum)
    }

    private fun agg(name: String, agg: Agg): Alias {
        val avg = when (agg) {
            Agg.avg -> Avg(Column(name))
            Agg.count -> Count(Column(name))
            Agg.sum -> Sum(Column(name))
        }
        selection += avg
        return avg
    }

    override val sql
        get() = "selection ${selection.map { it.sql }.joinToString()}"
}

infix fun Selection.from(init: From.() -> Unit): Query {
    val from = From()
    from.init()
    return Query(this, from)
}

fun select(init: Selection.() -> Unit): Selection {
    val select = Selection()
    select.init()
    return select
}

class Column(override val name: String) : Alias()