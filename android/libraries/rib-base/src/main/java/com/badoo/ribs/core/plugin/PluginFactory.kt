package com.badoo.ribs.core.plugin

import com.badoo.ribs.core.Node

typealias PluginFactory<V> = (Node<V>) -> List<Plugin<V>>

