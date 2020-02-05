package com.badoo.ribs.example.rib.simple4

import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.badoo.ribs.example.R
import com.badoo.ribs.example.rib.simple4.Simple4View.Dependency
import com.badoo.ribs.example.rib.simple4.Simple4View.Event.CheckPermissionsButtonClicked
import com.badoo.ribs.example.rib.simple4.Simple4View.Event.RequestPermissionsButtonClicked
import com.badoo.ribs.example.rib.simple4.Simple4View.ViewModel
import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface Simple4View : RibView,
    Consumer<ViewModel> {

        sealed class Event {
            object CheckPermissionsButtonClicked : Event()
            object RequestPermissionsButtonClicked : Event()
        }

        data class ViewModel(
            val text: String
        )

    interface Factory : ViewFactory<Dependency, Simple4View>

    data class Dependency(
        val feature: Simple4Feature,
        val analytics: Simple4Analytics
    )
}


class Simple4ViewImpl private constructor(
    override val androidView: ViewGroup,
    private val feature: Simple4Feature,
    private val analytics: Simple4Analytics
) : Simple4View {

    private val disposable = Observable.wrap(feature).subscribe(this)

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_foobar
    ) : Simple4View.Factory {
        override fun invoke(deps: Dependency): (ViewGroup) -> Simple4View = {
            Simple4ViewImpl(
                androidView = inflate(it, layoutRes),
                feature = deps.feature,
                analytics = deps.analytics
            )
        }
    }

    private val text: TextView = androidView.findViewById(R.id.foobar_debug)
    private val checkButton: Button = androidView.findViewById(R.id.foobar_button_check_permissions)
    private val requestButton: Button = androidView.findViewById(R.id.foobar_button_request_permissions)

    init {
        checkButton.setOnClickListener {
            analytics.accept(CheckPermissionsButtonClicked)
            feature.accept(CheckPermissionsButtonClicked)
        }
        requestButton.setOnClickListener {
            analytics.accept(RequestPermissionsButtonClicked)
            feature.accept(RequestPermissionsButtonClicked)
        }
        accept(ViewModel("Simple4ViewImpl"))
    }

    override fun accept(vm: ViewModel) {
        text.text = vm.text
    }

    // FIXME override
    fun onDestroy() {
        disposable.dispose()
    }
}
