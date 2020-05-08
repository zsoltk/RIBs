package com.badoo.ribs.example.rib.small

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams

class SmallNode(
    buildParams: BuildParams<Nothing?>,
    viewFactory: ((ViewGroup) -> SmallView?)?,
    val plugins: List<Plugin>
) : Node<SmallView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), Small {

}
