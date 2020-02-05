package com.badoo.ribs.example.rib.simple3

import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.builder.MviRxJava
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.badoo.ribs.example.R
import com.badoo.ribs.example.rib.simple3.Simple3View.Event
import com.badoo.ribs.example.rib.simple3.Simple3View.Event.CheckPermissionsButtonClicked
import com.badoo.ribs.example.rib.simple3.Simple3View.Event.RequestPermissionsButtonClicked
import com.badoo.ribs.example.rib.simple3.Simple3View.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface Simple3View : RibView,
    MviRxJava.View<Event, ViewModel> {

        sealed class Event {
            object CheckPermissionsButtonClicked : Event()
            object RequestPermissionsButtonClicked : Event()
        }

        data class ViewModel(
            val text: String
        )

    interface Factory : ViewFactory<Nothing?, Simple3View>
}


class Simple3ViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : Simple3View,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_foobar
    ) : Simple3View.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> Simple3View = {
            Simple3ViewImpl(
                inflate(it, layoutRes)
            )
        }
    }

    private val text: TextView = androidView.findViewById(R.id.foobar_debug)
    private val checkButton: Button = androidView.findViewById(R.id.foobar_button_check_permissions)
    private val requestButton: Button = androidView.findViewById(R.id.foobar_button_request_permissions)

    init {
        checkButton.setOnClickListener { events.accept(CheckPermissionsButtonClicked)}
        requestButton.setOnClickListener { events.accept(RequestPermissionsButtonClicked)}
        accept(ViewModel("Simple3ViewImpl"))
    }

    override fun accept(vm: ViewModel) {
        text.text = vm.text
    }
}
