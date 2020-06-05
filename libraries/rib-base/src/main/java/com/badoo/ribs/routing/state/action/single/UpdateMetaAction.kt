package com.badoo.ribs.routing.state.action.single

import android.os.Parcelable
import com.badoo.ribs.core.Node
import com.badoo.ribs.routing.action.RoutingAction
import com.badoo.ribs.routing.activator.RoutingActivator
import com.badoo.ribs.routing.state.RoutingContext.ActivationState
import com.badoo.ribs.routing.state.RoutingContext.ActivationState.INACTIVE
import com.badoo.ribs.routing.state.RoutingContext.Resolved
import com.badoo.ribs.routing.state.action.ActionExecutionParams
import com.badoo.ribs.routing.state.feature.RoutingStatePool.Effect.Individual.Deactivated
import com.badoo.ribs.routing.state.feature.RoutingStatePool.Effect.Individual.PendingDeactivateTrue
import com.badoo.ribs.routing.state.feature.EffectEmitter
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.state.feature.RoutingStatePool
import com.badoo.ribs.routing.state.feature.RoutingStatePool.Effect.Individual.MetaUpdated
import com.badoo.ribs.routing.transition.TransitionDirection
import com.badoo.ribs.routing.transition.TransitionElement

/**
 * Detaches views of associated [Node]s to a parentNode, and cleans up the associated [RoutingAction].
 *
 * Will not detach the [Node]s on the logical level, they are kept alive without their views.
 */
internal class UpdateMetaAction<C : Parcelable>(
    private val emitter: EffectEmitter<C>,
    private val routing: Routing<C>,
    private var item: Resolved<C>,
    private val parentNode: Node<*>,
    private val activator: RoutingActivator<C>,
    private val oldMeta: Parcelable,
    private val newMeta: Parcelable
) : RoutingTransitionAction<C> {

    class Factory(
        private val oldMeta: Parcelable,
        private val newMeta: Parcelable
    ): ActionFactory {
        override fun <C : Parcelable> create(
            params: ActionExecutionParams<C>
        ): RoutingTransitionAction<C> = UpdateMetaAction(
            emitter = params.transactionExecutionParams.emitter,
            routing = params.routing,
            item = params.item,
            parentNode = params.transactionExecutionParams.parentNode,
            activator = params.transactionExecutionParams.activator,
            oldMeta = oldMeta,
            newMeta = newMeta
        )
    }

    override var canExecute: Boolean =
        true

    override var transitionElements: List<TransitionElement<C>> =
        emptyList()

    override fun onBeforeTransition() {
        // TODO Consider doing this closer to Router (in result of RoutingActivator)
        transitionElements = item.nodes.mapNotNull {
            it.view?.let { ribView ->
                TransitionElement(
                    configuration = item.routing.configuration, // TODO consider passing the whole RoutingElement
                    direction = TransitionDirection.Meta(oldMeta, newMeta),
                    addedOrRemoved = false,
                    parentViewGroup = parentNode.targetViewGroupForChild(it),
                    identifier = it.identifier,
                    view = ribView.androidView
                )
            }
        }
    }

    override fun onTransition(forceExecute: Boolean) {
    }

    override fun onFinish(forceExecute: Boolean) {
        emitter.onNext(
            MetaUpdated(
                routing, item.copy(
                    routing = item.routing.copy(
                        meta = newMeta
                    )
                )
            )
        )
    }
}
