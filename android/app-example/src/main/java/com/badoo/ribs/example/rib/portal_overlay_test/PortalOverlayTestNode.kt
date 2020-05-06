package com.badoo.ribs.example.rib.portal_overlay_test

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.PluginFactory
import com.badoo.ribs.core.builder.BuildParams

class PortalOverlayTestNode(
    buildParams: BuildParams<*>,
    viewFactory: ((ViewGroup) -> PortalOverlayTestView?)?,
    pluginFactory: PluginFactory<PortalOverlayTestView>
) : Node<PortalOverlayTestView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    pluginFactory = pluginFactory
), PortalOverlayTest {

}
