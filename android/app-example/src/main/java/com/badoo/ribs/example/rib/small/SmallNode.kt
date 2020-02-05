package com.badoo.ribs.example.rib.small

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.core.Node

class SmallNode(
    savedInstanceState: Bundle?,
    viewFactory: ((ViewGroup) -> SmallView?)?,
    private val router: SmallRouter,
    private val interactor: SmallInteractor
) : Node<SmallView>(
    savedInstanceState = savedInstanceState,
    identifier = object : Small {},
    viewFactory = viewFactory,
    plugins = listOf(interactor, router)
), Small.Workflow {

}
