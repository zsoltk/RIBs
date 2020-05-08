package com.badoo.ribs.core.routing.configuration

import android.os.Parcelable
import com.badoo.ribs.core.routing.action.RoutingAction

interface ConfigurationResolver<C : Parcelable> {
    // TODO
//    fun resolveConfiguration(backStackElement: BackStackElement<C>): RoutingAction

    fun resolveConfiguration(configuration: C): RoutingAction
}
