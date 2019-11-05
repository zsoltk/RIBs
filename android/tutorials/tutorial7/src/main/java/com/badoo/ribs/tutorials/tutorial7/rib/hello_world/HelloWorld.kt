package com.badoo.ribs.tutorials.tutorial7.rib.hello_world

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflateOnDemand
import com.badoo.ribs.tutorials.tutorial7.R
import com.badoo.ribs.tutorials.tutorial7.util.Lexem
import com.badoo.ribs.tutorials.tutorial7.util.User
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface HelloWorld : Rib {

    interface Dependency {
        fun helloWorldInput(): ObservableSource<Input>
        fun helloWorldOutput(): Consumer<Output>
        fun user(): User
        fun config(): Configuration
    }

    sealed class Input {
        data class UpdateButtonText(val text: Lexem): Input()
    }

    sealed class Output {
        object HelloThere : Output()
        object MoreOptionsRequested : Output()
    }

    data class Configuration(
        val welcomeMessage: Lexem
    )

    class Customisation(
        val viewFactory: ViewFactory<HelloWorldView> = inflateOnDemand(
            R.layout.rib_hello_world
        )
    )
}
