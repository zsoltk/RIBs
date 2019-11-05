package com.badoo.ribs.tutorials.tutorial7.rib.greetings_container.builder

import com.badoo.ribs.core.Node
import com.badoo.ribs.tutorials.tutorial7.R
import com.badoo.ribs.tutorials.tutorial7.rib.greetings_container.GreetingsContainer
import com.badoo.ribs.tutorials.tutorial7.rib.greetings_container.GreetingsContainerInteractor
import com.badoo.ribs.tutorials.tutorial7.rib.greetings_container.GreetingsContainerRouter
import com.badoo.ribs.tutorials.tutorial7.rib.hello_world.HelloWorld
import com.badoo.ribs.tutorials.tutorial7.rib.hello_world.builder.HelloWorldBuilder
import com.badoo.ribs.tutorials.tutorial7.rib.option_selector.OptionSelector
import com.badoo.ribs.tutorials.tutorial7.rib.option_selector.builder.OptionSelectorBuilder
import com.badoo.ribs.tutorials.tutorial7.util.Lexem
import dagger.Provides
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

@dagger.Module
internal object GreetingsContainerModule {

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    @JvmSuppressWildcards
    internal fun options(): List<Lexem> =
        listOf(
            R.string.greeting_1,
            R.string.greeting_2,
            R.string.greeting_3,
            R.string.greeting_4
        ).map { Lexem.Resource(it) }

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun router(
        // pass component to child rib builders, or remove if there are none
        component: GreetingsContainerComponent
    ): GreetingsContainerRouter =
        GreetingsContainerRouter(
            helloWorldBuilder = HelloWorldBuilder(component),
            optionSelectorBuilder = OptionSelectorBuilder(component)
        )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun interactor(
        router: GreetingsContainerRouter,
        output: Consumer<GreetingsContainer.Output>,
        options: @JvmSuppressWildcards List<Lexem>
    ): GreetingsContainerInteractor =
        GreetingsContainerInteractor(
            router = router,
            output = output,
            options = options
        )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun node(
        router: GreetingsContainerRouter,
        interactor: GreetingsContainerInteractor
    ) : Node<Nothing> = Node(
        identifier = object : GreetingsContainer {},
        viewFactory = null,
        router = router,
        interactor = interactor
    )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun helloWorldConfig(): HelloWorld.Configuration =
        HelloWorld.Configuration(
            welcomeMessage = Lexem.Resource(
                R.string.hello_world_welcome_text
            )
        )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun helloWorldOutputConsumer(
        interactor: GreetingsContainerInteractor
    ) : Consumer<HelloWorld.Output> =
        interactor.helloWorldOutputConsumer

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun helloWorldInputSource(
        interactor: GreetingsContainerInteractor
    ) : ObservableSource<HelloWorld.Input> =
        interactor.helloWorldInputSource

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun moreOptionsConfig(
        options: @JvmSuppressWildcards List<Lexem>
    ): OptionSelector.Config =
        OptionSelector.Config(
            options = options
        )

    @GreetingsContainerScope
    @Provides
    @JvmStatic
    internal fun moreOptionsOutput(
        interactor: GreetingsContainerInteractor
    ): Consumer<OptionSelector.Output> =
        interactor.moreOptionsOutputConsumer
}
