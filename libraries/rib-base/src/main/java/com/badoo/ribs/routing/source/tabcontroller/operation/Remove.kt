package com.badoo.ribs.routing.source.tabcontroller.operation

import android.os.Parcelable
import com.badoo.ribs.routing.source.tabcontroller.State

class Remove<C : Parcelable>(
    private val configuration: C
) : Operation<C> {

    override fun isApplicable(state: State<C>): Boolean =
        state.any { it.routing.configuration == configuration }

    override fun invoke(state: State<C>): State<C> = state.copy(
        history = state.history + listOf(state.current),
        current = state.current - state.find { it.routing.configuration == configuration }!!
    )
}
