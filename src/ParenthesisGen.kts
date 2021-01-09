import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class State (
        private val order: Int,
        var openCnt: Int,
        var closeCnt: Int,
        var content: String) {

    fun canPushOpen(): Boolean {
        return openCnt < order
    }

    fun pushOpen(): State{
        return State(
                order,
                openCnt + 1,
                closeCnt,
                "$content("
        )
    }

    fun canPushClose(): Boolean {
        return closeCnt < order && closeCnt < openCnt
    }

    fun pushClose(): State {
        return State(
                order,
                openCnt,
                closeCnt + 1,
                "$content)"
        )
    }

    fun isFinalState(): Boolean {
        return content.length == order * 2
    }

    override fun hashCode(): Int{
        return content.hashCode()
    }

}

fun sol(n: Int): List<String>{
    val queue: Queue<State> = LinkedList()
    val result: MutableList<String> = ArrayList()
    val closeSet: MutableSet<State> = HashSet()
    queue.add(
            State(n, 0, 0,"")
    )

    while (!queue.isEmpty()) {
        val state = queue.poll()
        if (closeSet.contains(state))
            continue
        if (state.isFinalState()) {
            result.add(state.content)
            continue
        }
        if (state.canPushClose()) {
            queue.add(state.pushClose())
        }

        if (state.canPushOpen()) {
            queue.add(state.pushOpen())
        }
        closeSet.add(state)
    }
    return result
}

println(sol(3))