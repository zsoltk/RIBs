package com.badoo.ribs.example.rib.big

import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.Interactor

class BigInteractor(
    private val buildParams: BuildParams<Nothing?>,
    private val router: BigRouter
) : Interactor<BigView>(
    buildParams = buildParams,
    backPressHandler = null
) {

    override fun onViewCreated(view: BigView, viewLifecycle: Lifecycle) {
        val uuid = buildParams.identifier.uuid.toString()
        view.accept(BigView.ViewModel("My id: " + uuid.replace("${BigInteractor::class.java.name}.", "")))
    }
}
