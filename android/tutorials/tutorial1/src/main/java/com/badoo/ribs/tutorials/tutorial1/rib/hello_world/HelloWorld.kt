package com.badoo.ribs.tutorials.tutorial1.rib.hello_world

import com.badoo.ribs.core.Concept
import io.reactivex.functions.Consumer

interface HelloWorld : Concept {

    interface Dependency {
        fun helloWorldOutput(): Consumer<Output>
    }

    sealed class Output {
        object HelloThere : Output()
    }

    class Customisation(
        val viewFactory: HelloWorldView.Factory = HelloWorldViewImpl.Factory()
    )
}
