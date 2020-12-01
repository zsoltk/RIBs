package com.badoo.ribs.sandbox.migration_demo.leaf.mapper

import com.badoo.ribs.sandbox.migration_demo.leaf.LeafView.Event
import com.badoo.ribs.sandbox.migration_demo.leaf.feature.LeafFeature.Wish

internal object ViewEventToWish : (Event) -> Wish? {

    override fun invoke(event: Event): Wish? =
        TODO("Implement LeafViewEventToWish mapping")
}
