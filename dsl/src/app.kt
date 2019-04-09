fun main() {
    println(
        select {
            col("name").alias("name")
            avg("salary").alias("average_salary")
            count("*").alias("total")
        } from {
            table("users")
        } where {
            col<String>("name") eq "Vasya"
            col<Double>("salary") eq 100.0
        } grouping {
            col("name")
        } ordering {
            col("name").desc()
        }
    )
}