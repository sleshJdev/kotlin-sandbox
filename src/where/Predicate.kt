package where

import select.Column

class Predicate(
    val col: Column
) {
    override fun toString(): String = col.toString()
}