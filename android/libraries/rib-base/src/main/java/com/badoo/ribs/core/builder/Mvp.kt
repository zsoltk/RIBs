package com.badoo.ribs.core.builder

import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.Builder
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewPlugin


open class Mvp<View : RibView, Presenter>(
    private val viewFactory: (ViewGroup) -> View,
    private val presenterFactory: (View) -> Presenter,
    private val viewPlugins: Set<ViewPlugin> = emptySet()
): Builder<Unit>() {

    override val dependency: Unit = Unit

    fun build(savedInstanceState: Bundle?): Node<View> =
        Node(
            savedInstanceState = savedInstanceState,
            viewFactory = viewFactory,
            identifier = object : Rib {},
            interactor = MvpInteractor(savedInstanceState, presenterFactory),
            router = null,
            viewPlugins = viewPlugins
        )

    private class MvpInteractor<View : RibView, Presenter>(
        savedInstanceState: Bundle?,
        private val presenterFactory: (View) -> Presenter
    ): Interactor<View>(
        savedInstanceState = savedInstanceState,
        disposables = null
    ) {

        override fun onViewCreated(view: View, viewLifecycle: Lifecycle) {
            presenterFactory.invoke(view)
        }
    }
}
