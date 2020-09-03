package com.badoo.ribs.routing.source.tabcontroller.operation

import android.os.Parcelable
import com.badoo.ribs.routing.source.tabcontroller.State

interface Operation<C : Parcelable>: (State<C>) -> State<C> {
    fun isApplicable(state: State<C>): Boolean
}


