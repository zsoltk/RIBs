package com.badoo.ribs.core.routing.configuration.feature

import android.os.Parcelable
import com.badoo.ribs.core.routing.configuration.feature.operation.BackStack
import kotlinx.android.parcel.Parcelize

// TODO rename
@Parcelize
data class BackStackFeatureState<C : Parcelable>(
    val backStack: BackStack<C> = emptyList()
) : Parcelable {

    val current: BackStackElement<C>?
        get() = backStack.lastOrNull()
}

@Parcelize
data class BackStackElement<C : Parcelable>(
    val configuration: C,
    val overlays: List<C> = emptyList()
) : Parcelable
