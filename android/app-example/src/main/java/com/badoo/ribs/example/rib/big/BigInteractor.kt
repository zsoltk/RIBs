package com.badoo.ribs.example.rib.big

import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.BackStackInteractor
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.example.rib.big.BigRouter.Configuration

class BigInteractor(
    private val buildParams: BuildParams<*>
) : BackStackInteractor<Configuration, BigView>(
    buildParams = buildParams,
    initialConfiguration = Configuration.Content.Default
) {

    override fun onViewCreated(view: BigView, viewLifecycle: Lifecycle) {
        val uuid = buildParams.identifier.uuid.toString()
        view.accept(BigView.ViewModel("My id: " + uuid.replace("${BigInteractor::class.java.name}.", "")))
    }
}
