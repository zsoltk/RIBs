package com.badoo.ribs.example.rib.foo_bar

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.Plugin

class FooBarNode(
    viewFactory: ((ViewGroup) -> FooBarView?)?,
    interactor: FooBarInteractor,
    buildParams: BuildParams<*>,
    plugins: List<Plugin<FooBarView>>
) : Node<FooBarView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = listOf(interactor) + plugins
), FooBar {

}
