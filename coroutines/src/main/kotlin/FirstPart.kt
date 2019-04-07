import kotlinx.coroutines.*
import kotlin.random.Random

val handler = CoroutineExceptionHandler { _, exception ->
    println("Caught $exception")
}

suspend fun getCounters1(): List<Int> {
    return withContext(Dispatchers.Default) {
        (1..3).map { id ->
            async {
                getCounter(id)
            }
        }.awaitAll()
    }
}

suspend fun getCounters2(): List<Int> {
    return withContext(Dispatchers.Default + handler) {
        (1..3).map { id ->
            async {
                withTimeout(5000) {
                    getCounter(id)
                }
            }
        }.awaitAll()
    }
}

suspend fun getCounters3(): List<Int> {
    return withContext(Dispatchers.Default + handler) {
        (1..3).map { id ->
            async {
                try {
                    getCounter(id)
                } catch (e: Exception) {
                    println("task $id failed due to $e.message")
                    0
                }
            }
        }.awaitAll()
    }
}

suspend fun getCounters4(): List<Int> {
    return withContext(Dispatchers.Default + handler) {
        (1..3).map { id ->
            async {
                withTimeoutOrNull(5000) {
                    getCounter(id)
                } ?: 0
            }
        }.awaitAll()
    }
}

suspend fun getCounter(id: Int): Int {
    println("$id start")
    val duration = Random.nextLong(0, 10000)
    if (duration < 1000) {
        throw RuntimeException("Duration less than 1000ms")
    }
    delay(duration)
    println("$id end")
    return id * 3
}

fun main() = runBlocking {
    println("1. Отменять все задания если одно из заданий упало:")
    try {
        println(getCounters1())
    } catch (e: Exception) { println(e.message) }

    println("2. Отменять все задания если одно из заданий выполняется дольше 5000мс")
    try {
        println(getCounters2())
    } catch (e: Exception) { println(e.message) }

    println("3. Если задание упало - возвращать дефолтовое значение (например 0)")
    try {
        println(getCounters3())
    } catch (e: Exception) { println(e.message) }

    println("4. Если задание выполняется больше 5000мс - возвращать дефолтовое значение")
    try {
        println(getCounters4())
    } catch (e: Exception) { println(e.message) }
}