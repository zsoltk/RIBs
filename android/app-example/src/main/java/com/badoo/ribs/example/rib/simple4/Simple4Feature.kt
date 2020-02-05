package com.badoo.ribs.example.rib.simple4

import com.badoo.ribs.core.builder.MviRxJava
import com.badoo.ribs.example.rib.simple4.Simple4View.Event
import com.badoo.ribs.example.rib.simple4.Simple4View.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observer

class Simple4Feature : MviRxJava.StateStore<Event, ViewModel> {
    override fun accept(event: Event) {
    }

    override fun subscribe(observer: Observer<in ViewModel>) {
        BehaviorRelay.createDefault<ViewModel>(
            ViewModel("Fake state store view model")
        ).subscribe(observer)
    }
}
