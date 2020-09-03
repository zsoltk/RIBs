package com.badoo.ribs.routing.source.tabcontroller.operation

import android.os.Parcelable
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.history.RoutingHistoryElement
import com.badoo.ribs.routing.source.tabcontroller.State

class Init<C : Parcelable>(
    private val configurations: Collection<C>,
    private val active: C
) : Operation<C> {

    override fun isApplicable(state: State<C>): Boolean =
        state.current.isEmpty()

    override fun invoke(state: State<C>): State<C> =
        state.copy(
            current = configurations.map { configuration ->
                RoutingHistoryElement(
                    routing = Routing(
                        configuration = configuration,
                        identifier = Routing.Identifier(
                            configuration.toString()
                        )
                    ),
                    activation = if (configuration == active) RoutingHistoryElement.Activation.ACTIVE else RoutingHistoryElement.Activation.INACTIVE,
                    overlays = emptyList()
                )
            }.toSet()
        )
}
