package com.badoo.ribs.example.rib.small

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams

class SmallNode(
    buildParams: BuildParams<Nothing?>,
    viewFactory: ((ViewGroup) -> SmallView?)?,
    private val router: SmallRouter,
    private val interactor: SmallInteractor
) : Node<SmallView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = listOf(interactor, router)
), Small {

}
