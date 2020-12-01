package com.badoo.ribs.sandbox.migration_demo.container.mapper

import com.badoo.ribs.sandbox.migration_demo.container.ContainerView.ViewModel
import com.badoo.ribs.sandbox.migration_demo.container.feature.ContainerFeature.State

internal object StateToViewModel : (State) -> ViewModel {

    override fun invoke(state: State): ViewModel =
        TODO("Implement StateToViewModel mapping")
}
