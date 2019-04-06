import select.*

fun main() {
    val query =
        select {
            col("name").alias("name")
            col("email").alias("email")
        } from {
            table = "users"
        } where {
            col("name")
        } grouping {
            col("name")
            col("email")
        } ordering {
            col("name").desc()
            col("email")
        }
    println(query)
}