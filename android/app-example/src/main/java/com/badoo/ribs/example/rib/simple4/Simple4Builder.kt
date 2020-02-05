package com.badoo.ribs.example.rib.simple4

import android.os.Bundle
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.RibViewOnly

class Simple4Builder {

    fun build(savedInstanceState: Bundle? = null): Node<Simple4View> =
        RibViewOnly(
            viewFactory = Simple4ViewImpl.Factory().invoke(
                Simple4View.Dependency(
                    feature = Simple4Feature(),
                    analytics = Simple4Analytics()
                )
            )
        ).build(savedInstanceState)
}
