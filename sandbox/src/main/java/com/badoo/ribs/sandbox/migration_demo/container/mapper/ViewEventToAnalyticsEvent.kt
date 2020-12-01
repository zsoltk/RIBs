package com.badoo.ribs.sandbox.migration_demo.container.mapper

import com.badoo.ribs.sandbox.migration_demo.container.ContainerView.Event
import com.badoo.ribs.sandbox.migration_demo.container.analytics.ContainerAnalytics
import com.badoo.ribs.sandbox.migration_demo.container.analytics.ContainerAnalytics.Event.ViewEvent

internal object ViewEventToAnalyticsEvent : (Event) -> ContainerAnalytics.Event? {

    override fun invoke(event: Event): ContainerAnalytics.Event? =
        ViewEvent(event)
}
