package com.badoo.ribs.core.routing.action

import com.badoo.ribs.core.Concept
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildContext

interface RoutingAction {

    val nbConcepts: Int

    /**
     * Guaranteed by framework to receive a list of nbNodesToBuild elements
     */
    fun build(buildContexts: List<BuildContext>): List<Concept<*>> =
        emptyList()

    fun execute() {
    }

    fun cleanup() {
    }

    fun anchor(): Node<*>? =
        null

    companion object {
        fun noop(): RoutingAction = object : RoutingAction {
            override val nbConcepts: Int = 0
        }
    }
}


