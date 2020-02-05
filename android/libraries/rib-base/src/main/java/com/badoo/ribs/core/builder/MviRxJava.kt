package com.badoo.ribs.core.builder

import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewPlugin
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

class MviRxJava<Intent : Any, State : Any>(
    private val viewFactory: (ViewGroup) -> View<Intent, State>,
    private val stateStore: StateStore<Intent, State>,
    private val viewPlugins: Set<ViewPlugin> = emptySet()
) {

    interface View<Intent : Any, State : Any> : Consumer<State>, ObservableSource<Intent>, RibView
    interface StateStore<Intent : Any, State : Any> : Consumer<Intent>, ObservableSource<State>

    fun build(savedInstanceState: Bundle?): Node<View<Intent, State>> =
        Node(
            savedInstanceState = savedInstanceState,
            viewFactory = viewFactory,
            identifier = object : Rib {},
            interactor = MviRxJavaInteractor(savedInstanceState, stateStore),
            router = null,
            viewPlugins = viewPlugins
        )

    private class MviRxJavaInteractor<Intent : Any, State : Any>(
        savedInstanceState: Bundle?,
        private val stateStore: StateStore<Intent, State>
    ): Interactor<View<Intent, State>>(
        savedInstanceState = savedInstanceState,
        disposables = null
    ) {
        override fun onViewCreated(view: View<Intent, State>, viewLifecycle: Lifecycle) {
            viewLifecycle.startStop {
                bind(view to stateStore)
                bind(stateStore to view)
            }
        }
    }
}
