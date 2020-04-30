package com.badoo.ribs.core.helper

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.ConceptView
import com.nhaarman.mockitokotlin2.mock

class TestView : ConceptView {

    override val androidView: ViewGroup = mock()

    override fun getParentViewForChild(child: Node<*>) =
        androidView
}
