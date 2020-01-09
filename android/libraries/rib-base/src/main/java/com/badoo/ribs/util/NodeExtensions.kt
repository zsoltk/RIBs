package com.badoo.ribs.util

import android.util.Log
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.TreeChangeListener
import com.badoo.ribs.core.ViewTreeChangeListener
import com.badoo.ribs.util.FindNodeTask.Policy
import java.util.Queue
import java.util.concurrent.LinkedBlockingQueue

data class FindNodeTask(
    val name: String,
    val predicate: (Node<*>) -> Boolean,
    val childSelector: (Node<*>) -> List<Node<*>>,
    val policy: Policy
) {
    sealed class Policy {
        object AnyNumberAllowed : Policy()
        object OneOrZeroAllowed : Policy()
        object ExactlyOneAllowed : Policy()
    }
}

fun Node<*>.findNodes(task: FindNodeTask): List<Node<*>> {
    val queue: Queue<Node<*>> = LinkedBlockingQueue()
    queue.acceptChildren(listOf(this))

    while (queue.peek() != null) {
        val sameLevelElements = mutableListOf<Node<*>>()
        while (queue.peek() != null) {
            sameLevelElements.add(queue.poll())
        }

        val fittingElements = sameLevelElements.filter(task.predicate)

        when {
            fittingElements.size > 1 -> when (task.policy) {
                Policy.AnyNumberAllowed -> return fittingElements
                Policy.OneOrZeroAllowed,
                Policy.ExactlyOneAllowed -> throw RuntimeException(
                    "Collision on filter ${task.name} -- " +
                        "found multiple elements on the same level that fit: $fittingElements"
                )
            }
            fittingElements.size == 1 -> return fittingElements
        }

        sameLevelElements.forEach { child ->
            queue.acceptChildren(task.childSelector.invoke(child))
        }
    }

    // TODO remove logging
    Log.d("FindNodeTask", "--- finished ---")

    return when (task.policy) {
        Policy.AnyNumberAllowed -> emptyList()
        Policy.OneOrZeroAllowed -> emptyList()
        Policy.ExactlyOneAllowed -> throw NoSuchElementException(
            "Empty set after filter ${task.name} -- " +
                "expected 1, but 0 was found"
        )
    }
}

private fun Queue<Node<*>>.acceptChildren(nodes: List<Node<*>>) {
    nodes.forEach {
        // TODO remove logging
        Log.d("FindNodeTask", "Adding to queue: $it")
        offer(it)
    }
}

@JvmName("addTreeChangeListener")
fun MutableList<TreeChangeListener>.add(block: () -> Unit) {
    add(object : TreeChangeListener {
        override fun onTreeChange() {
            block()
        }
    })
}

@JvmName("addViewTreeChangeListener")
fun MutableList<ViewTreeChangeListener>.add(block: () -> Unit) {
    add(object : ViewTreeChangeListener {
        override fun onViewTreeChange() {
            block()
        }
    })
}
