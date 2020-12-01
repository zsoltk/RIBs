package com.badoo.ribs.sandbox.migration_demo.container.mapper

import com.badoo.ribs.sandbox.migration_demo.container.Container.Output
import com.badoo.ribs.sandbox.migration_demo.container.feature.ContainerFeature.News

internal object NewsToOutput : (News) -> Output? {

    override fun invoke(news: News): Output? =
        TODO("Implement ContainerNewsToOutput mapping")
}
