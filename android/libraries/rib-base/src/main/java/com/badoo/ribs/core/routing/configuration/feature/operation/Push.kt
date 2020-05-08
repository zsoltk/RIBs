package com.badoo.ribs.core.routing.configuration.feature.operation

import android.os.Parcelable
import com.badoo.ribs.core.routing.configuration.feature.RoutingElement
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeature

data class Push<C : Parcelable>(
    private val configuration: C
) : BackStackOperation<C> {
    override fun isApplicable(backStack: BackStack<C>): Boolean =
        configuration != backStack.current?.configuration

    override fun invoke(backStack: BackStack<C>): BackStack<C> =
        backStack + RoutingElement(configuration)

    private val BackStack<C>.current: RoutingElement<C>?
        get() = this.lastOrNull()
}

fun <C : Parcelable> BackStackFeature<C>.push(configuration: C) {
    accept(BackStackFeature.Operation(Push(configuration)))
}
