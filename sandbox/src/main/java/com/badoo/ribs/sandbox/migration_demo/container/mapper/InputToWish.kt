package com.badoo.ribs.sandbox.migration_demo.container.mapper

import com.badoo.ribs.sandbox.migration_demo.container.Container.Input
import com.badoo.ribs.sandbox.migration_demo.container.feature.ContainerFeature.Wish

internal object InputToWish : (Input) -> Wish? {

    override fun invoke(event: Input): Wish? =
        TODO("Implement ContainerInputToWish mapping")
}
