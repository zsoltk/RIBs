package com.badoo.ribs.tutorials.tutorial5.rib.greetings_container

import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Router
import com.badoo.ribs.tutorials.tutorial5.rib.greetings_container.GreetingsContainer.Output.GreetingsSaid
import com.badoo.ribs.tutorials.tutorial5.rib.greetings_container.GreetingsContainerRouter.Configuration
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorld
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorld.Input.UpdateButtonText
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorld.Output.HelloThere
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorld.Output.MoreOptionsRequested
import com.badoo.ribs.tutorials.tutorial5.rib.option_selector.OptionSelector.Output
import com.badoo.ribs.tutorials.tutorial5.util.Lexem
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.functions.Consumer

class GreetingsContainerInteractor(
    router: Router<Configuration, Nothing, Configuration, Nothing, Nothing>,
    output: Consumer<GreetingsContainer.Output>,
    private val options: List<Lexem>
) : Interactor<Configuration, Configuration, Nothing, Nothing>(
    router = router,
    disposables = null
) {
    private var idx: Int = 0
    private val greeting: Lexem
        get() = options[idx]

    internal val helloWorldInputSource: Relay<HelloWorld.Input> = PublishRelay.create()
    internal val helloWorldOutputConsumer: Consumer<HelloWorld.Output> = Consumer {
        when (it) {
            HelloThere -> output.accept(GreetingsSaid(greeting))
            MoreOptionsRequested -> router.push(Configuration.MoreOptions(idx))
        }
    }

    internal val moreOptionsOutputConsumer: Consumer<Output> = Consumer {
        when (it) {
            is Output.SelectionConfirmed -> {
                router.popBackStack()
                idx = it.selectionIndex
                helloWorldInputSource.accept(
                    UpdateButtonText(greeting)
                )
            }
        }
    }
}
