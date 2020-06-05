package com.badoo.ribs.routing.transition

import android.os.Parcelable

sealed class TransitionDirection {

    object Enter : TransitionDirection()

    object Exit : TransitionDirection()

    data class Meta(
        val old: Parcelable,
        val new: Parcelable
    ) : TransitionDirection()
}
