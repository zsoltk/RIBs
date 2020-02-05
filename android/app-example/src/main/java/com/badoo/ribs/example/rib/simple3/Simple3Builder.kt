package com.badoo.ribs.example.rib.simple3

import android.os.Bundle
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.MviRxJava
import com.badoo.ribs.core.builder.MviRxJava.View
import com.badoo.ribs.example.rib.simple3.Simple3View.Event
import com.badoo.ribs.example.rib.simple3.Simple3View.ViewModel

class Simple3Builder {

    private val viewFactory = Simple3ViewImpl.Factory().invoke(null)
    private val stateStore = Simple3Feature()

    fun build(savedInstanceState: Bundle? = null): Node<View<Event, ViewModel>> =
        MviRxJava(
            viewFactory = viewFactory,
            stateStore = stateStore
        ).build(savedInstanceState)
}
