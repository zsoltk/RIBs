package com.badoo.ribs.core.routing.configuration.feature

import android.os.Parcelable
import com.badoo.ribs.core.routing.configuration.feature.operation.BackStack
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

// TODO rename
@Parcelize
data class BackStackFeatureState<C : Parcelable>(
    val backStack: BackStack<C> = emptyList()
) : Parcelable {

    val current: RoutingElement<C>?
        get() = backStack.lastOrNull()
}


@Parcelize
data class RoutingElement<C : Parcelable>(
    val configuration: C,
    val identifier: Identifier = Identifier(), // differ will use this to match elements between two "back stack" states, instead of list position
    val isActive: Boolean = false,  // differ will not make an assumption that last one is always active, instead allows it to be controlled via this flag
    val meta: Serializable = 0,     // unused for now, can be used with describing changes meaningful only to client code (e.g. configuration moved inside active window)
    val overlays: List<RoutingElement<C>> = emptyList()
) : Parcelable {

    @Parcelize
    data class Identifier(
        val id: Int = 0
    ) : Parcelable

    // TODO use this in pool + resolver, but find better name
    @Parcelize
    data class PoolElement<C : Parcelable>(
        val configuration: C,
        val identifier: Identifier = Identifier(),
        val meta: Serializable = 0
    ) : Parcelable

    fun toPoolElement(): PoolElement<C> =
        PoolElement(
            configuration = configuration,
            identifier = identifier,
            meta = meta
        )
}

