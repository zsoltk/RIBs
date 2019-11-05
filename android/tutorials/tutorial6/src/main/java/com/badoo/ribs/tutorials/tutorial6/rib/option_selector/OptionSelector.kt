package com.badoo.ribs.tutorials.tutorial6.rib.option_selector

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.RibCustomisation
import com.badoo.ribs.customisation.inflateOnDemand
import com.badoo.ribs.tutorials.tutorial6.R
import com.badoo.ribs.tutorials.tutorial6.rib.option_selector.OptionSelectorView
import com.badoo.ribs.tutorials.tutorial6.util.Lexem
import io.reactivex.functions.Consumer

interface OptionSelector : Rib {

    interface Dependency {
        fun moreOptionsOutput(): Consumer<Output>
        fun moreOptionsConfig(): Config
    }

    data class Config(
        val options: List<Lexem>
    )

    sealed class Output {
        data class SelectionConfirmed(val selectionIndex: Int) : Output()
    }

    class Customisation(
        val viewFactory: ViewFactory<OptionSelectorView> = inflateOnDemand(
            R.layout.rib_option_selector
        )
    ) : RibCustomisation
}
