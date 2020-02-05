package com.badoo.ribs.example.rib.simple2

import android.os.Bundle
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.RibViewOnly

class Simple2Builder {

    fun build(savedInstanceState: Bundle? = null): Node<Simple2View> =
        RibViewOnly(
            viewFactory = Simple2ViewImpl.Factory().invoke(null)
        ).build(savedInstanceState)
}
