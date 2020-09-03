package com.badoo.ribs.routing.source.tabcontroller.operation

import android.os.Parcelable
import com.badoo.ribs.routing.source.tabcontroller.State

class Drop<C : Parcelable> :
    Operation<C> {

    override fun isApplicable(state: State<C>): Boolean =
        true

    override fun invoke(state: State<C>): State<C> =
        state.copy(
            history = state.history.drop(1)
        )

}
