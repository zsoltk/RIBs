package com.badoo.ribs.tutorials.tutorial3.rib.hello_world.builder

import com.badoo.ribs.core.Node
import com.badoo.ribs.tutorials.tutorial3.rib.hello_world.HelloWorld
import com.badoo.ribs.tutorials.tutorial3.rib.hello_world.HelloWorldView
import dagger.BindsInstance

@HelloWorldScope
@dagger.Component(
    modules = [HelloWorldModule::class],
    dependencies = [HelloWorld.Dependency::class]
)
internal interface HelloWorldComponent {

    @dagger.Component.Factory
    interface Factory {
        fun create(
            dependency: HelloWorld.Dependency,
            @BindsInstance customisation: HelloWorld.Customisation
        ): HelloWorldComponent
    }

    fun node(): Node<HelloWorldView>
}
