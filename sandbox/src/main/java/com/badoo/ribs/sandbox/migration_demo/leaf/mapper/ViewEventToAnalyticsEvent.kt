package com.badoo.ribs.sandbox.migration_demo.leaf.mapper

import com.badoo.ribs.sandbox.migration_demo.leaf.LeafView.Event
import com.badoo.ribs.sandbox.migration_demo.leaf.analytics.LeafAnalytics
import com.badoo.ribs.sandbox.migration_demo.leaf.analytics.LeafAnalytics.Event.ViewEvent

internal object ViewEventToAnalyticsEvent : (Event) -> LeafAnalytics.Event? {

    override fun invoke(event: Event): LeafAnalytics.Event? =
        ViewEvent(event)
}
