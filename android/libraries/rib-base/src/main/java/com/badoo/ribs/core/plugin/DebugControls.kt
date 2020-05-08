package com.badoo.ribs.core.plugin

import android.view.View
import android.view.ViewGroup
import com.badoo.ribs.core.Node

open class DebugControls(
    private val viewFactory: ((ViewGroup) -> View)? = null,
    private val viewGroupForChildren: (() -> ViewGroup)? = null,
    private val isEnabled: Boolean // TODO consider this for all plugins
) : NodeAware,
    RibLifecycleAware {

    private lateinit var node: Node<*>
    private var target: ViewGroup? = null
    var debugView: View? = null

    override fun init(node: Node<*>) {
        this.node = node
    }

    final override fun onAttachToView(parentViewGroup: ViewGroup) {
        if (isEnabled) {
            val lazy = node.pluginUp<DebugControls>()?.viewGroupForChildren ?: viewGroupForChildren
            target = lazy?.invoke()
            target?.let {
                debugView = viewFactory?.invoke(it)
                debugView?.let {
                    target?.addView(it)
                    onDebugViewCreated(it)
                }
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
