package com.badoo.ribs.sandbox.migration_demo.leaf.mapper

import com.badoo.ribs.sandbox.migration_demo.leaf.Leaf.Output
import com.badoo.ribs.sandbox.migration_demo.leaf.feature.LeafFeature.News

internal object NewsToOutput : (News) -> Output? {

    override fun invoke(news: News): Output? =
        TODO("Implement LeafNewsToOutput mapping")
}
