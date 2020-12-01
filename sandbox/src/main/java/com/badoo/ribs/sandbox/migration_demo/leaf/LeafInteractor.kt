package com.badoo.ribs.sandbox.migration_demo.leaf

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.sandbox.migration_demo.leaf.analytics.LeafAnalytics
import com.badoo.ribs.sandbox.migration_demo.leaf.feature.LeafFeature
import com.badoo.ribs.sandbox.migration_demo.leaf.mapper.InputToWish
import com.badoo.ribs.sandbox.migration_demo.leaf.mapper.NewsToOutput
import com.badoo.ribs.sandbox.migration_demo.leaf.mapper.StateToViewModel
import com.badoo.ribs.sandbox.migration_demo.leaf.mapper.ViewEventToAnalyticsEvent
import com.badoo.ribs.sandbox.migration_demo.leaf.mapper.ViewEventToWish
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

internal class LeafInteractor(
    buildParams: BuildParams<*>,
    private val input: ObservableSource<Leaf.Input>,
    private val output: Consumer<Leaf.Output>,
    private val feature: LeafFeature
) : Interactor<LeafView>(
    buildParams = buildParams,
    disposables = feature
) {

    override fun onAttach(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            bind(feature.news to output using NewsToOutput)
            bind(input to feature using InputToWish)
        }
    }

    override fun onViewCreated(view: LeafView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            bind(feature to view using StateToViewModel)
            bind(view to feature using ViewEventToWish)
            bind(view to LeafAnalytics using ViewEventToAnalyticsEvent)
        }
    }
}
