package com.badoo.ribs.core.plugin

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.RibView

open class DebugControls<V : RibView>(
    private val node: Node<*>,
    private val viewFactory: ((ViewGroup) -> View)? = null,
    private val debugParentViewGroup: ViewGroup? = null,
    private val isEnabled: Boolean // TODO consider this for all plugins
) : Plugin<V> {

    private var target: ViewGroup? = null
    var debugView: View? = null

    final override fun onViewCreated(view: V, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)
        if (isEnabled) {
            target = node.pluginUp<DebugControls<*>>()?.debugParentViewGroup ?: debugParentViewGroup
            target?.let {
                debugView = viewFactory?.invoke(it)
            }
        }
    }

    final override fun onAttachToView(parentViewGroup: ViewGroup) {
        if (isEnabled && target != null) {
            debugView?.let {
                target?.addView(it)
                onDebugViewCreated(it)
            }
        }
    }

    final override fun onDetachFromView(parentViewGroup: ViewGroup) {
        super.onDetachFromView(parentViewGroup)
        if (isEnabled && target != null) {
            debugView?.let {
                target?.removeView(it)
                onDebugViewDestroyed(it)
            }
            debugView = null
            target = null
        }
    }

    open fun onDebugViewCreated(debugView: View) {}

    open fun onDebugViewDestroyed(debugView: View) {}
}
