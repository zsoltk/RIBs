package com.badoo.ribs.sandbox.migration_demo.container.mapper

import com.badoo.ribs.sandbox.migration_demo.container.ContainerView.Event
import com.badoo.ribs.sandbox.migration_demo.container.feature.ContainerFeature.Wish

internal object ViewEventToWish : (Event) -> Wish? {

    override fun invoke(event: Event): Wish? =
        TODO("Implement ContainerViewEventToWish mapping")
}
