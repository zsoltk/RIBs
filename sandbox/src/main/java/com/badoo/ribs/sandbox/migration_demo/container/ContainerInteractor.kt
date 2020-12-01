package com.badoo.ribs.sandbox.migration_demo.container

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.sandbox.migration_demo.container.analytics.ContainerAnalytics
import com.badoo.ribs.sandbox.migration_demo.container.feature.ContainerFeature
import com.badoo.ribs.sandbox.migration_demo.container.mapper.InputToWish
import com.badoo.ribs.sandbox.migration_demo.container.mapper.NewsToOutput
import com.badoo.ribs.sandbox.migration_demo.container.mapper.StateToViewModel
import com.badoo.ribs.sandbox.migration_demo.container.mapper.ViewEventToAnalyticsEvent
import com.badoo.ribs.sandbox.migration_demo.container.mapper.ViewEventToWish
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

internal class ContainerInteractor(
    buildParams: BuildParams<*>,
    private val router: ContainerRouter,
    private val input: ObservableSource<Container.Input>,
    private val output: Consumer<Container.Output>,
    private val feature: ContainerFeature
) : Interactor<ContainerView>(
    buildParams = buildParams,
    disposables = feature
) {

    override fun onAttach(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            bind(feature.news to output using NewsToOutput)
            bind(input to feature using InputToWish)
        }
    }

    override fun onViewCreated(view: ContainerView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            bind(feature to view using StateToViewModel)
            bind(view to feature using ViewEventToWish)
            bind(view to ContainerAnalytics using ViewEventToAnalyticsEvent)
        }
    }
}
