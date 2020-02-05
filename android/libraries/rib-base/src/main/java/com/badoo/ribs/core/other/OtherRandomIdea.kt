package com.badoo.ribs.core.other

import android.view.ViewGroup
import android.widget.FrameLayout


private fun usage1() {

    Node {
        // onCreate block with this
        val feature = Unit

        ViewFactory {
            val view = FrameLayout(it.context)
            ViewScope(view) {
                onBackPressed { false }
                onDestroy {  }
            }
        }

        router = Unit

        onBackPressed { false }
        onDestroy {}
    }
}

private class Node(
    block: NodeScope.() -> Unit
)

private class NodeScope {

    var router: Unit? = null

    fun ViewFactory(block: (ViewGroup) -> ViewScope) {
    }

    fun Router(block: RouterScope.() -> Unit) {
    }

    fun onBackPressed(block: () -> Boolean) {
    }

    fun onDestroy(block: () -> Unit) {
    }
}

private class RouterScope(
    val backStack: List<Unit>
) {

}

private class ViewScope(
    private val view: ViewGroup,
    private val block: ViewScope.() -> Unit
) {

    fun onBackPressed(block: () -> Boolean) {
    }

    fun onDestroy(block: () -> Unit) {
    }
}
