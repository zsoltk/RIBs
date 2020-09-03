package com.badoo.ribs.routing.source.tabcontroller.operation

import android.os.Parcelable
import com.badoo.ribs.routing.history.RoutingHistoryElement.Activation.ACTIVE
import com.badoo.ribs.routing.history.RoutingHistoryElement.Activation.INACTIVE
import com.badoo.ribs.routing.source.tabcontroller.State
import com.badoo.ribs.routing.source.tabcontroller.TabController

class SetActive<C : Parcelable>(
    private val configuration: C
) : Operation<C> {

    override fun isApplicable(state: State<C>): Boolean =
        state.any { it.routing.configuration == configuration && it.activation != ACTIVE }

    override fun invoke(state: State<C>): State<C> {
        val target = state.find { it.routing.configuration == configuration && it.activation != ACTIVE }!!
        val targetActivated = target.copy(activation = ACTIVE)
        val theRest = state - target
        val theRestDeactivated = theRest.map { it.copy(activation = INACTIVE) }

        return state.copy(
            history = state.history + listOf(state.current),
            current = (theRestDeactivated + targetActivated).toSet()
        )
    }
}

fun <C : Parcelable> TabController<C>.setActive(configuration: C) {
    accept(SetActive(configuration))
}
