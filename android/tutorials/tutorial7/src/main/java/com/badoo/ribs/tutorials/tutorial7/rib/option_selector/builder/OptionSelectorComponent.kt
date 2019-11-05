package com.badoo.ribs.tutorials.tutorial7.rib.option_selector.builder

import com.badoo.ribs.core.Node
import com.badoo.ribs.tutorials.tutorial7.rib.option_selector.OptionSelector
import com.badoo.ribs.tutorials.tutorial7.rib.option_selector.OptionSelectorView

@OptionSelectorScope
@dagger.Component(
    modules = [OptionSelectorModule::class],
    dependencies = [
        OptionSelector.Dependency::class,
        OptionSelector.Customisation::class,
        OptionSelectorBuilder.BuildParams::class
    ]
)
internal interface OptionSelectorComponent {

    @dagger.Component.Factory
    interface Factory {
        fun create(
            dependency: OptionSelector.Dependency,
            customisation: OptionSelector.Customisation,
            buildParams: OptionSelectorBuilder.BuildParams
        ): OptionSelectorComponent
    }

    fun node(): Node<OptionSelectorView>
}
