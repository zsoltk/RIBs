package com.badoo.ribs.example.rib.lorem_ipsum

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.example.util.CanReportViewScreen

class LoremIpsumNode(
    viewFactory: ((ViewGroup) -> LoremIpsumView?)?,
    interactor: LoremIpsumInteractor,
    savedInstanceState: Bundle?
) : Node<LoremIpsumView>(
    savedInstanceState = savedInstanceState,
    identifier = object : LoremIpsum {},
    viewFactory = viewFactory,
    interactor = interactor,
    router = null
), LoremIpsum.Workflow, CanReportViewScreen {

    override val screenName: String
        get() = "SCREEN_NAME_LOREM_IPSUM"
}
