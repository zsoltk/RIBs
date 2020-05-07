package com.badoo.ribs.core.plugin

import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.RibView

interface Plugin

interface SystemAware : Plugin {
    fun onLowMemory() {}
}

interface LifecycleAware : Plugin {
    fun onStart() {}

    fun onStop() {}

    fun onResume() {}

    fun onPause() {}

    fun onSaveInstanceState(outState: Bundle) {}
}

interface NodeAware : Plugin {
    fun init(node: Node<*>) {}
}

interface ViewAware<V : RibView> {
    fun onViewCreated(view: V, viewLifecycle: Lifecycle) {}
}

interface AttachAware : Plugin {
    fun onAttach(nodeLifecycle: Lifecycle) {}

    fun onAttachToView(parentViewGroup: ViewGroup) {}

    fun onDetachFromView(parentViewGroup: ViewGroup) {}

    fun onDetach() {}
}

interface SubtreeChangeAware : Plugin {
    fun onAttachChildNode(child: Node<*>) {}

    fun onAttachChildView(child: Node<*>) {}

    fun onDetachChildView(child: Node<*>) {}

    fun onDetachChildNode(child: Node<*>) {}
}


interface BackPressHandler : Plugin {
    fun handleBackPressBeforeDownstream(): Boolean =
        false

    fun handleBackPressAfterDownstream(): Boolean =
        false
}
