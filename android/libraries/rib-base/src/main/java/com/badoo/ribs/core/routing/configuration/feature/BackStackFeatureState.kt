package com.badoo.ribs.core.routing.configuration.feature

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class BackStackFeatureState<C : Parcelable>(
    val currentBackStack: List<BackStackElement<C>> = emptyList(),
    val previousBackStack: List<BackStackElement<C>> = emptyList()
) : Parcelable {

    val current: BackStackElement<C>?
        get() = currentBackStack.lastOrNull()

    val currentOverlay: C?
        get() = current?.overlays?.lastOrNull()

    val canPopOverlay: Boolean
        get() = currentBackStack.lastOrNull()?.overlays?.isNotEmpty() == true

    val canPopContent: Boolean
        get() = currentBackStack.size > 1

    val canPop: Boolean
        get() = canPopContent || canPopOverlay
}

@Parcelize
data class BackStackElement<C : Parcelable>(
    val configuration: C,
    val overlays: List<C> = emptyList()
) : Parcelable
