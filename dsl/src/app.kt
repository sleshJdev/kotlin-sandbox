fun main() {
    println(
        select {
            col("name").alias("name")
            avg("salary").alias("average_salary")
            count("*").alias("total")
        } from {
            table("users")
        } where {
        } grouping {
            col("name")
        } ordering {
            col("name").desc()
        }
    )
}