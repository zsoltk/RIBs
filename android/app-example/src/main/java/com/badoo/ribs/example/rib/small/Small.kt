package com.badoo.ribs.example.rib.small

import com.badoo.ribs.core.Concept
import com.badoo.ribs.customisation.CanProvidePortal
import com.badoo.ribs.customisation.RibCustomisation

interface Small : Concept<SmallView> {

    interface Dependency :
        CanProvidePortal

    class Customisation(
        val viewFactory: SmallView.Factory = SmallViewImpl.Factory()
    ) : RibCustomisation

    interface Workflow
}
