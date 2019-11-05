package com.badoo.ribs.tutorials.tutorial7.rib.option_selector.mapper

import com.badoo.ribs.tutorials.tutorial7.rib.option_selector.OptionSelector.Output
import com.badoo.ribs.tutorials.tutorial7.rib.option_selector.OptionSelectorView
import com.badoo.ribs.tutorials.tutorial7.rib.option_selector.OptionSelectorView.Event.ConfirmSelectionButtonClicked

internal object ViewEventToOutput : (OptionSelectorView.Event) -> Output? {

    override fun invoke(event: OptionSelectorView.Event): Output? =
        when (event) {
            is ConfirmSelectionButtonClicked -> Output.SelectionConfirmed(event.selectionIndex)
        }
}
