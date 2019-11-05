package com.badoo.ribs.tutorials.tutorial5.rib.option_selector

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Router
import com.badoo.ribs.tutorials.tutorial5.rib.option_selector.OptionSelector.Output
import com.badoo.ribs.tutorials.tutorial5.rib.option_selector.OptionSelectorRouter.Configuration
import com.badoo.ribs.tutorials.tutorial5.rib.option_selector.mapper.ViewEventToOutput
import com.badoo.ribs.tutorials.tutorial5.util.Lexem
import io.reactivex.functions.Consumer

class OptionSelectorInteractor(
    savedInstanceState: Bundle?,
    router: Router<Configuration, *, Configuration, Nothing, OptionSelectorView>,
    private val output: Consumer<Output>,
    options: List<Lexem>,
    initialSelectionIndex: Int?
) : Interactor<Configuration, Configuration, Nothing, OptionSelectorView>(
    savedInstanceState = savedInstanceState,
    router = router,
    disposables = null
) {

    override fun onViewCreated(view: OptionSelectorView, viewLifecycle: Lifecycle) {
        view.accept(initialViewModel)
        viewLifecycle.createDestroy {
            bind(view to output using ViewEventToOutput)
        }
    }

    private val initialViewModel = OptionSelectorView.ViewModel(
        options,
        initialSelectionIndex
    )
}
