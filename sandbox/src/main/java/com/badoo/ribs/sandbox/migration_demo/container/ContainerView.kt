package com.badoo.ribs.sandbox.migration_demo.container

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.badoo.ribs.sandbox.R
import com.badoo.ribs.sandbox.migration_demo.container.ContainerView.Event
import com.badoo.ribs.sandbox.migration_demo.container.ContainerView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface ContainerView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface Factory : ViewFactory<Nothing?, ContainerView>
}


class ContainerViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : ContainerView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_container
    ) : ContainerView.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> ContainerView = {
            ContainerViewImpl(
                inflate(it, layoutRes)
            )
        }
    }

    override fun accept(vm: ContainerView.ViewModel) {
    }
}
