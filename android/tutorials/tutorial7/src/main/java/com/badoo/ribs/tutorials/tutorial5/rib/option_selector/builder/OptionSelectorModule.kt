package com.badoo.ribs.tutorials.tutorial5.rib.option_selector.builder

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.tutorials.tutorial5.rib.option_selector.OptionSelector
import com.badoo.ribs.tutorials.tutorial5.rib.option_selector.OptionSelector.Output
import com.badoo.ribs.tutorials.tutorial5.rib.option_selector.OptionSelectorInteractor
import com.badoo.ribs.tutorials.tutorial5.rib.option_selector.OptionSelectorRouter
import com.badoo.ribs.tutorials.tutorial5.rib.option_selector.OptionSelectorView
import dagger.Provides
import io.reactivex.functions.Consumer

@dagger.Module
internal object OptionSelectorModule {

    @OptionSelectorScope
    @Provides
    @JvmStatic
    internal fun router(): OptionSelectorRouter =
        OptionSelectorRouter()

    @OptionSelectorScope
    @Provides
    @JvmStatic
    internal fun interactor(
        router: OptionSelectorRouter,
        output: Consumer<Output>,
        config: OptionSelector.Config,
        buildParams: OptionSelectorBuilder.BuildParams
    ): OptionSelectorInteractor =
        OptionSelectorInteractor(
            router = router,
            output = output,
            options = config.options,
            initialSelectionIndex = buildParams.selectionIndex
        )

    @OptionSelectorScope
    @Provides
    @JvmStatic
    internal fun node(
        viewFactory: ViewFactory<OptionSelectorView>,
        router: OptionSelectorRouter,
        interactor: OptionSelectorInteractor
    ) : Node<OptionSelectorView> = Node(
        identifier = object : OptionSelector {},
        viewFactory = viewFactory,
        router = router,
        interactor = interactor
    )
}
