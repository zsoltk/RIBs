package com.badoo.ribs.core.builder

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.core.Builder
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewPlugin

class RibViewOnly<V : RibView>(
    private val viewFactory: (ViewGroup) -> V,
    private val viewPlugins: Set<ViewPlugin> = emptySet()
): Builder<Unit>() {

    override val dependency: Unit = Unit

    fun build(savedInstanceState: Bundle?): Node<V> =
        Node(
            savedInstanceState = savedInstanceState,
            viewFactory = viewFactory,
            identifier = object : Rib {},
            interactor = ViewOnlyInteractor(savedInstanceState),
            router = null,
            viewPlugins = viewPlugins
        )

    private class ViewOnlyInteractor<V : RibView>(
        savedInstanceState: Bundle?
    ): Interactor<V>(
        savedInstanceState = savedInstanceState,
        disposables = null
    )
}
