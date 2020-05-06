package com.badoo.ribs.example.rib.hello_world

import android.util.Log
import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.plugin.PluginFactory
import com.badoo.ribs.core.builder.BuildParams
import io.reactivex.Single

class HelloWorldNode(
    viewFactory: ((ViewGroup) -> HelloWorldView?)?,
    buildParams: BuildParams<*>,
    pluginFactory: PluginFactory<HelloWorldView>
) : Node<HelloWorldView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    pluginFactory = pluginFactory
), HelloWorld {

    override fun somethingSomethingDarkSide(): Single<HelloWorld> =
        executeWorkflow {
            Log.d("WORKFLOW", "Hello world / somethingSomethingDarkSide")
        }
}
