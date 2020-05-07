package com.badoo.ribs.example.rib.dialog_example

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.plugin.PluginFactory
import com.badoo.ribs.core.builder.BuildParams

class DialogExampleNode(
    buildParams: BuildParams<*>,
    viewFactory: ((ViewGroup) -> DialogExampleView?)?,
    pluginFactory: PluginFactory<DialogExampleView>
) : Node<DialogExampleView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = pluginFactory
), DialogExample
