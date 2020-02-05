package com.badoo.ribs.core.builder

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewPlugin
import com.badoo.ribs.customisation.inflate

open class ViewOnly(
    private val viewFactory: (ViewGroup) -> ViewGroup,
    private val viewPlugins: Set<ViewPlugin> = emptySet()
) {
    constructor(
        @LayoutRes layoutResId: Int,
        viewPlugins: Set<ViewPlugin> = emptySet()
    ) : this(
        viewFactory = { parentViewGroup ->
            inflate(parentViewGroup, layoutResId)
        },
        viewPlugins = viewPlugins
    )

    fun build(savedInstanceState: Bundle?): Node<RibView> =
        Node(
            savedInstanceState = savedInstanceState,
            viewFactory = SimpleViewFactory(viewFactory),
            identifier = object : Rib {},
            interactor = ViewOnlyInteractor(savedInstanceState),
            router = null,
            viewPlugins = viewPlugins
        )

    private class SimpleViewFactory(
        private val viewFactory: (ViewGroup) -> ViewGroup
    ): (ViewGroup) -> RibView {
        override fun invoke(parentViewGroup: ViewGroup): RibView =
            object : RibView {
                override val androidView: ViewGroup =
                    viewFactory.invoke(parentViewGroup)
            }
    }

    private class ViewOnlyInteractor(
        savedInstanceState: Bundle?
    ): Interactor<RibView>(
        savedInstanceState = savedInstanceState,
        disposables = null
    )
}
