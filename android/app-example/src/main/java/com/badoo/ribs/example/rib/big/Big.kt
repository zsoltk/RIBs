package com.badoo.ribs.example.rib.big

import com.badoo.ribs.core.Concept
import com.badoo.ribs.customisation.CanProvidePortal
import com.badoo.ribs.customisation.RibCustomisation

interface Big : Concept<BigView> {

    interface Dependency :
        CanProvidePortal

    class Customisation(
        val viewFactory: BigView.Factory = BigViewImpl.Factory()
    ) : RibCustomisation
}
