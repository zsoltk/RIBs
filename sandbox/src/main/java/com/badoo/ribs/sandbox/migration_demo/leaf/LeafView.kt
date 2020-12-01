package com.badoo.ribs.sandbox.migration_demo.leaf

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.badoo.ribs.sandbox.R
import com.badoo.ribs.sandbox.migration_demo.leaf.LeafView.Event
import com.badoo.ribs.sandbox.migration_demo.leaf.LeafView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface LeafView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface Factory : ViewFactory<Nothing?, LeafView>
}


class LeafViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : LeafView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_leaf
    ) : LeafView.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> LeafView = {
            LeafViewImpl(
                inflate(it, layoutRes)
            )
        }
    }

    override fun accept(vm: LeafView.ViewModel) {
    }
}
