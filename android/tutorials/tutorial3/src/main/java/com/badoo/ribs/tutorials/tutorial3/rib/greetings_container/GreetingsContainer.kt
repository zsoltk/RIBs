package com.badoo.ribs.tutorials.tutorial3.rib.greetings_container

import com.badoo.ribs.core.Concept
import io.reactivex.functions.Consumer

interface GreetingsContainer : Concept {

    interface Dependency {
        fun greetingsContainerOutput(): Consumer<Output>
    }

    sealed class Output {
        data class GreetingsSaid(val greeting: String) : Output()
    }
}
