package where

import select.Column

class Where {
    private val predicates: MutableList<Predicate> = mutableListOf()

    fun col(name: String): Predicate {
        val predicate = Predicate(Column(name))
        predicates += predicate
        return predicate
    }

    override fun toString(): String =
        if (predicates.isNotEmpty()) "where ${predicates.joinToString(" ")}" else ""
}