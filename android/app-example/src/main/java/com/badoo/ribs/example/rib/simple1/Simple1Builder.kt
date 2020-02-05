package com.badoo.ribs.example.rib.simple1

import android.os.Bundle
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.ViewOnly
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.example.R

class Simple1Builder {

    fun build(savedInstanceState: Bundle? = null): Node<RibView> =
        ViewOnly(layoutResId = R.layout.rib_foobar)
            .build(savedInstanceState)
}
