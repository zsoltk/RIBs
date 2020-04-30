package com.badoo.ribs.core.routing.action

import com.badoo.ribs.core.Concept
import com.badoo.ribs.core.builder.BuildContext

class CompositeRoutingAction(
    private vararg val routingActions: RoutingAction
) : RoutingAction {

    override val nbConcepts: Int = routingActions.fold(0) { acc, r -> acc + r.nbConcepts }

    constructor(routingActions: List<RoutingAction>) : this(*routingActions.toTypedArray())

    override fun build(buildContexts: List<BuildContext>) : List<Concept<*>> =
        routingActions.mapIndexed { index, routingAction ->
            routingAction.build(
                buildContexts = listOfNotNull(buildContexts.getOrNull(index))
            )
        }.flatten()

    override fun execute() {
        routingActions.forEach {
            it.execute()
        }
    }

    override fun cleanup() {
        routingActions.forEach {
            it.cleanup()
        }
    }

    companion object {
        fun composite(vararg routingActions: RoutingAction): RoutingAction =
            CompositeRoutingAction(*routingActions)
    }
}
