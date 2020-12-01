package com.badoo.ribs.sandbox.migration_demo.leaf.mapper

import com.badoo.ribs.sandbox.migration_demo.leaf.Leaf.Input
import com.badoo.ribs.sandbox.migration_demo.leaf.feature.LeafFeature.Wish

internal object InputToWish : (Input) -> Wish? {

    override fun invoke(event: Input): Wish? =
        TODO("Implement LeafInputToWish mapping")
}
