package com.badoo.ribs.core.routing.action

import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.Node

interface RoutingAction<V : RibView> {

    // FIXME what if you have a mix (composite) - this should be paired with the ribs being created
    val allowAttachView: Boolean
        get() = true

    fun createRibs() : List<Node<*>> =
        emptyList()

    fun execute() {

    }

    fun cleanup()  {
    }

    companion object {
        fun <V : RibView> noop(): RoutingAction<V> = object :
            RoutingAction<V> {}
    }
}


