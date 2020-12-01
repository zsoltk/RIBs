package com.badoo.ribs.sandbox.migration_demo.leaf.analytics

import com.badoo.ribs.sandbox.migration_demo.leaf.LeafView
import io.reactivex.functions.Consumer

internal object LeafAnalytics : Consumer<LeafAnalytics.Event> {

    sealed class Event {
        data class ViewEvent(val event: LeafView.Event) : Event()
    }

    override fun accept(event: LeafAnalytics.Event) {
        // TODO Implement tracking
    }
}
