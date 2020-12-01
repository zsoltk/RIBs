package com.badoo.ribs.sandbox.migration_demo.container

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeature
import com.badoo.ribs.sandbox.migration_demo.container.analytics.ContainerAnalytics
import com.badoo.ribs.sandbox.migration_demo.container.feature.ContainerFeature
import com.badoo.ribs.sandbox.migration_demo.container.mapper.InputToWish
import com.badoo.ribs.sandbox.migration_demo.container.mapper.NewsToOutput
import com.badoo.ribs.sandbox.migration_demo.container.mapper.StateToViewModel
import com.badoo.ribs.sandbox.migration_demo.container.mapper.ViewEventToAnalyticsEvent
import com.badoo.ribs.sandbox.migration_demo.container.mapper.ViewEventToWish
import com.badoo.ribs.sandbox.migration_demo.container.routing.ContainerRouter.Configuration

internal class ContainerInteractor(
    buildParams: BuildParams<*>,
    private val backStack: BackStackFeature<Configuration>,
    private val feature: ContainerFeature
) : Interactor<Container, ContainerView>(
    buildParams = buildParams,
    disposables = feature
) {

    override fun onAttach(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            bind(feature.news to rib.output using NewsToOutput)
            bind(rib.input to feature using InputToWish)
        }
    }

    override fun onViewCreated(view: ContainerView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            bind(feature to view using StateToViewModel)
            bind(view to feature using ViewEventToWish)
            bind(view to ContainerAnalytics using ViewEventToAnalyticsEvent)
        }
    }

    override fun onChildCreated(child: Node<*>) {
        /**
         * TODO bind children here and delete this comment block.
         *
         *  At this point children haven't set their own bindings yet,
         *  so it's safe to setup listening to their output before they start emitting.
         *
         *  On the other hand, they're not ready to receive inputs yet. Usually this is alright.
         *  If it's a requirement though, create those bindings in [onAttachChild]
         */
        // child.lifecycle.createDestroy {
            // when (child) {
                // is Child1 -> bind(child.output to someConsumer)
            // }
        // }
    }
}
