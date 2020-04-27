package com.badoo.ribs.core.routing.action

import com.badoo.ribs.core.AttachMode
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildContext
import com.badoo.ribs.core.builder.NodeFactory
import com.badoo.ribs.core.view.RibView

open class AddToRecyclerViewRoutingAction<V : RibView>(
    private val nodeFactory: NodeFactory
) : RoutingAction<V> {

    override fun buildNodes(buildContexts: List<BuildContext>): List<Node<*>> =
        listOf(
            nodeFactory.invoke(
                buildContext.copy(
                    attachMode = AttachMode.EXTERNAL
                )
            )
        )

    companion object {
        fun <V : RibView> recyclerView(nodeFactory: NodeFactory): RoutingAction<V> =
            AddToRecyclerViewRoutingAction(nodeFactory)
    }
}
