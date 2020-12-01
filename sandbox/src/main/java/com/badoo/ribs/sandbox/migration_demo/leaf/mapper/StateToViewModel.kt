package com.badoo.ribs.sandbox.migration_demo.leaf.mapper

import com.badoo.ribs.sandbox.migration_demo.leaf.LeafView.ViewModel
import com.badoo.ribs.sandbox.migration_demo.leaf.feature.LeafFeature.State

internal object StateToViewModel : (State) -> ViewModel {

    override fun invoke(state: State): ViewModel =
        TODO("Implement StateToViewModel mapping")
}
