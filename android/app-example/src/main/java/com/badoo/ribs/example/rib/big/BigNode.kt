package com.badoo.ribs.example.rib.big

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.plugin.PluginFactory
import com.badoo.ribs.core.builder.BuildParams

class BigNode(
    buildParams: BuildParams<*>,
    viewFactory: ((ViewGroup) -> BigView?)?,
    pluginFactory: PluginFactory<BigView>
) : Node<BigView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    pluginFactory = pluginFactory
), Big
