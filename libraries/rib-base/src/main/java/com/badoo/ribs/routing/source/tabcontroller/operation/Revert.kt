package com.badoo.ribs.routing.source.tabcontroller.operation

import android.os.Parcelable
import com.badoo.ribs.routing.source.tabcontroller.State

class Revert<C : Parcelable> :
    Operation<C> {

    override fun isApplicable(state: State<C>): Boolean =
        state.history.isNotEmpty()

    override fun invoke(state: State<C>): State<C> =
        state.copy(
            history = state.history.dropLast(1),
            current = state.history.last()
        )

}
