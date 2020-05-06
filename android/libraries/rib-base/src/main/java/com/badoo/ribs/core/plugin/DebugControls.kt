package com.badoo.ribs.core.plugin

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.RibView

abstract class DebugControls<V : RibView>(
    private val node: Node<*>,
    private val viewFactory: () -> View,
    private val debugParentViewGroup: ViewGroup? = null,
    private val isEnabled: Boolean // TODO consider this for all plugins
) : Plugin<V> {

    final override fun onViewCreated(view: V, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)
        if (isEnabled) {

        }
        // TODO create debug view
    }

    final override fun onAttachToView(parentViewGroup: ViewGroup) {
        val target = node.pluginUp<DebugControls<*>>()?.debugParentViewGroup ?: debugParentViewGroup
        // TODO target.addView(debugView)
    }

    abstract fun onDebugViewCreated(debugView: View)

    final override fun onDetachFromView(parentViewGroup: ViewGroup) {
        super.onDetachFromView(parentViewGroup)
        // TODO target.removeView(debugView)
        // TODO destroy debugView
    }
}
