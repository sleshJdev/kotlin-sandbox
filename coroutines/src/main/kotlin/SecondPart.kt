import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.runBlocking
import java.time.Instant

data class State(
    val counter: Long,
    val history: String
)

typealias ActionMsg = (State) -> State

@ObsoleteCoroutinesApi
fun main(): Unit = runBlocking {
    val actor = actor<ActionMsg> {
        var state = State(0, "")
        consumeEach {
            state = it.invoke(state)
            print("New state: $state")
        }
    }

    actor.send {
        State(
            it.counter + 1,
            "${it.history}\n${Instant.now()}"
        )
    }

    actor.close()

    println("End")
}