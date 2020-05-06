package com.badoo.ribs.example.rib.small

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.plugin.PluginFactory
import com.badoo.ribs.core.builder.BuildParams

class SmallNode(
    buildParams: BuildParams<Nothing?>,
    viewFactory: ((ViewGroup) -> SmallView?)?,
    val pluginFactory: PluginFactory<SmallView>
) : Node<SmallView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    pluginFactory = pluginFactory
), Small {

}
