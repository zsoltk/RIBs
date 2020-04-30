package com.badoo.ribs.example.rib.portal_overlay_test

import com.badoo.ribs.core.Concept
import com.badoo.ribs.customisation.RibCustomisation

interface PortalOverlayTest : Concept<PortalOverlayTestView> {

    interface Dependency

    class Customisation(
        val viewFactory: PortalOverlayTestView.Factory = PortalOverlayTestViewImpl.Factory()
    ) : RibCustomisation
}
