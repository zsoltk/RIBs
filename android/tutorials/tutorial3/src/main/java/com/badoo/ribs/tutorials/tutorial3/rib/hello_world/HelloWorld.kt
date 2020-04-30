package com.badoo.ribs.tutorials.tutorial3.rib.hello_world

import com.badoo.ribs.core.Concept

interface HelloWorld : Concept {

    interface Dependency

    class Customisation(
        val viewFactory: HelloWorldView.Factory = HelloWorldViewImpl.Factory()
    )
}
