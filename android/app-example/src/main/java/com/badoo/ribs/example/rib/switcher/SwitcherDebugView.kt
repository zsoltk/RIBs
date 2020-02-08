package com.badoo.ribs.example.rib.switcher

import com.badoo.ribs.core.DebugView
import com.badoo.ribs.example.R


class SwitcherDebugView(
//    workflow: Switcher.Workflow
    node: SwitcherNode
) : DebugView<SwitcherView>(
    node = node,
    layoutResId = R.layout.rib_debug_switcher,
    bindings = hashMapOf(
        R.id.debug_1 to node.attachHelloWorld(),
        R.id.debug_2 to node.doSomethingAndStayOnThisNode()
    )
)

