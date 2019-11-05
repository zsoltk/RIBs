package com.badoo.ribs.tutorials.tutorial6.rib.option_selector.builder

import com.badoo.ribs.core.Builder
import com.badoo.ribs.core.Node
import com.badoo.ribs.tutorials.tutorial6.rib.option_selector.OptionSelector
import com.badoo.ribs.tutorials.tutorial6.rib.option_selector.OptionSelectorView

class OptionSelectorBuilder(
    override val dependency: OptionSelector.Dependency
) : Builder<OptionSelector.Dependency>() {

    internal data class BuildParams(
        val selectionIndex: Int
    )

    fun build(selectionIndex: Int): Node<OptionSelectorView> =
        DaggerOptionSelectorComponent
            .factory()
            .create(
                dependency = dependency,
                customisation = OptionSelector.Customisation(),
                buildParams = BuildParams(
                    selectionIndex = selectionIndex
                )
            )
            .node()
}
