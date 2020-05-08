package com.badoo.ribs.core.routing.configuration

import android.os.Parcelable
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.core.routing.configuration.feature.RoutingElement

interface ConfigurationResolver<C : Parcelable> {
    // TODO
//    fun resolveConfiguration(backStackElement: BackStackElement<C>): RoutingAction

    fun resolve(routing: RoutingElement<C>): RoutingAction
}
