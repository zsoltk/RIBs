package com.badoo.ribs.sandbox.migration_demo.container.analytics

import com.badoo.ribs.sandbox.migration_demo.container.ContainerView
import io.reactivex.functions.Consumer

internal object ContainerAnalytics : Consumer<ContainerAnalytics.Event> {

    sealed class Event {
        data class ViewEvent(val event: ContainerView.Event) : Event()
    }

    override fun accept(event: ContainerAnalytics.Event) {
        // TODO Implement tracking
    }
}
