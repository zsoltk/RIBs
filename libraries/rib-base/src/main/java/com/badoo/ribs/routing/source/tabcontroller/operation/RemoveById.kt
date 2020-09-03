package com.badoo.ribs.routing.source.tabcontroller.operation

import android.os.Parcelable
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.source.tabcontroller.State

class RemoveById<C : Parcelable>(
    private val identifier: Routing.Identifier
) : Operation<C> {

    override fun isApplicable(state: State<C>): Boolean =
        state.any { it.routing.identifier == identifier }

    override fun invoke(state: State<C>): State<C> = state.copy(
        history = state.history + listOf(state.current),
        current = state.current - state.find { it.routing.identifier == identifier }!!
    )
}
