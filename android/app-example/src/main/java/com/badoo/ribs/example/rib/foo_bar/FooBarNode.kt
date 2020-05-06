package com.badoo.ribs.example.rib.foo_bar

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.plugin.PluginFactory
import com.badoo.ribs.core.builder.BuildParams

class FooBarNode(
    viewFactory: ((ViewGroup) -> FooBarView?)?,
    buildParams: BuildParams<*>,
    pluginFactory: PluginFactory<FooBarView>
) : Node<FooBarView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    pluginFactory = pluginFactory
), FooBar {

}
