package com.badoo.ribs.example.rib.lorem_ipsum

import com.badoo.ribs.core.Concept
import com.badoo.ribs.customisation.RibCustomisation
import io.reactivex.functions.Consumer

interface LoremIpsum : Concept<LoremIpsumView> {

    interface Dependency {
        fun loremIpsumOutput(): Consumer<Output>
    }

    sealed class Output {
        object SomeEvent : Output()
    }

    class Customisation(
        val viewFactory: LoremIpsumView.Factory = LoremIpsumViewImpl.Factory()
    ) : RibCustomisation
}
