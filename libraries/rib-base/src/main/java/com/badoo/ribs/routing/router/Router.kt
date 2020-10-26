package com.badoo.ribs.routing.router

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.NodeAware
import com.badoo.ribs.core.plugin.NodeLifecycleAware
import com.badoo.ribs.core.plugin.SavesInstanceState
import com.badoo.ribs.core.plugin.SubtreeBackPressHandler
import com.badoo.ribs.core.plugin.ViewLifecycleAware
import com.badoo.ribs.core.state.CompositeCancellable
import com.badoo.ribs.core.state.TimeCapsule
import com.badoo.ribs.routing.activator.ChildActivator
import com.badoo.ribs.routing.activator.RoutingActivator
import com.badoo.ribs.routing.activator.UnhandledChildActivator
import com.badoo.ribs.routing.resolver.RoutingResolver
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.source.changes
import com.badoo.ribs.routing.state.feature.RoutingStatePool
import com.badoo.ribs.routing.state.feature.Transaction.PoolCommand.SaveInstanceState
import com.badoo.ribs.routing.state.feature.Transaction.PoolCommand.Sleep
import com.badoo.ribs.routing.state.feature.Transaction.PoolCommand.WakeUp
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import io.reactivex.disposables.CompositeDisposable

abstract class Router<C : Parcelable>(
    buildParams: BuildParams<*>,
    protected val routingSource: RoutingSource<C>,
    private val transitionHandler: TransitionHandler<C>? = null,
    private val clientChildActivator: ChildActivator<C> = UnhandledChildActivator()
) : RoutingResolver<C>,
    NodeAware,
    NodeLifecycleAware,
    ViewLifecycleAware,
    SavesInstanceState,
    SubtreeBackPressHandler by routingSource {

    private val cancellable = CompositeCancellable()
    private val timeCapsule: TimeCapsule = TimeCapsule(buildParams.savedInstanceState)
    private val hasSavedState: Boolean  = buildParams.savedInstanceState != null

    private lateinit var routingStatePool: RoutingStatePool<C>
    override lateinit var node: Node<*>
    private lateinit var activator: RoutingActivator<C>

    override fun init(node: Node<*>) {
        this.node = node
        activator = RoutingActivator(node, clientChildActivator)
        initFeatures(node)
    }

    private fun initFeatures(node: Node<*>) {
        routingStatePool = RoutingStatePool(
            timeCapsule = timeCapsule,
            resolver = this,
            activator = activator,
            parentNode = node, // TODO remove
            transitionHandler = transitionHandler
        )

        cancellable += routingStatePool
    }

    override fun onSaveInstanceState(outState: Bundle) {
        routingSource.onSaveInstanceState(outState)
        routingStatePool.accept(SaveInstanceState())
        timeCapsule.saveState(outState)
    }

    override fun onCreate(nodeLifecycle: Lifecycle) {
        cancellable += routingSource.changes(hasSavedState).observe(routingStatePool::accept)
//    override fun onAttach(nodeLifecycle: Lifecycle) {
//        binder.bind(routingSource.changes(hasSavedState) to routingStatePool)
//        binder.bind(routingStatePool.news to Consumer {
//            if (it is RoutingStatePool.News.TransitionFinished) {
//                routingSource.onTransitionFinished()
//            }
//        })
    }

    override fun onAttachToView() {
        routingStatePool.accept(WakeUp())
    }

    override fun onDetachFromView() {
        routingStatePool.accept(Sleep())
    }

    override fun onDestroy() {
        // TODO consider extending Disposables plugin
        cancellable.cancel()
    }
}
