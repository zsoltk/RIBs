package com.badoo.ribs.routing.source.tabcontroller.operation

import android.os.Parcelable
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.history.RoutingHistoryElement
import com.badoo.ribs.routing.source.tabcontroller.State

class Add<C : Parcelable>(
    private val configuration: C
) : Operation<C> {

    override fun isApplicable(state: State<C>): Boolean =
        state.none { it.routing.configuration == configuration }

    override fun invoke(state: State<C>): State<C> =
        state.copy(
            history = state.history + listOf(state.current), // limit 1
            current = state.current + RoutingHistoryElement(
                routing = Routing(
                    configuration = configuration,
                    identifier = Routing.Identifier(
                        configuration.toString()
                    )
                ),
                activation = RoutingHistoryElement.Activation.INACTIVE,
                overlays = emptyList()
            )
        )
}
