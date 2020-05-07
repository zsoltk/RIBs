package com.badoo.ribs.example.rib.switcher.subtree

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Configuration : Parcelable {
    sealed class Permanent : Configuration() {
        @Parcelize object Menu : Permanent()
    }
    sealed class Content : Configuration() {
        @Parcelize object Hello : Content()
        @Parcelize object Foo : Content()
        @Parcelize object DialogsExample : Content()
        @Parcelize object Blocker : Content()
    }
    sealed class Overlay : Configuration() {
        @Parcelize object Dialog : Overlay()
    }
}
