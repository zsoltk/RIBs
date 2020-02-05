package com.badoo.ribs.example.rib.foo_bar

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.Plugin

class FooBarNode(
    viewFactory: ((ViewGroup) -> FooBarView?)?,
    interactor: FooBarInteractor,
    savedInstanceState: Bundle?,
    plugins: List<Plugin<FooBarView>>
) : Node<FooBarView>(
    savedInstanceState = savedInstanceState,
    identifier = object : FooBar {},
    viewFactory = viewFactory,
    plugins = listOf(interactor) + plugins
), FooBar.Workflow {

}
