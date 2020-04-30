package com.badoo.ribs.core.view

import android.view.ViewGroup
import com.badoo.ribs.core.Node

interface ConceptView {

    val androidView: ViewGroup

    fun getParentViewForChild(child: Node<*>): ViewGroup? =
        androidView
}
