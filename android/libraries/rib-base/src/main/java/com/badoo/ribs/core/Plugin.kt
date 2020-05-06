package com.badoo.ribs.core

import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.view.RibView

typealias PluginFactory<V> = (Node<V>) -> List<Plugin<V>>

interface Plugin<V : RibView> {

    fun init(node: Node<V>) {}

    fun onAttach(nodeLifecycle: Lifecycle) {}

    fun onViewCreated(view: V, viewLifecycle: Lifecycle) {}

    fun onAttachToView(parentViewGroup: ViewGroup) {}

    fun onDetachFromView(parentViewGroup: ViewGroup) {}

    fun onDetach() {}

    fun onAttachChildNode(child: Node<*>) {}

    fun onAttachChildView(child: Node<*>) {}

    fun onDetachChildView(child: Node<*>) {}

    fun onDetachChildNode(child: Node<*>) {}

    fun onSaveInstanceState(outState: Bundle) {}

    fun onLowMemory() {}

    fun handleBackPressBeforeDownstream(): Boolean =
        false

    fun handleBackPressAfterDownstream(): Boolean =
        false
}
