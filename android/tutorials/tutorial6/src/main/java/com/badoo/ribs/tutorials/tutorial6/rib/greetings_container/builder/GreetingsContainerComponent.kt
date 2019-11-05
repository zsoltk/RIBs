package com.badoo.ribs.tutorials.tutorial6.rib.greetings_container.builder

import com.badoo.ribs.core.Node
import com.badoo.ribs.tutorials.tutorial6.rib.greetings_container.GreetingsContainer
import com.badoo.ribs.tutorials.tutorial6.rib.hello_world.HelloWorld
import com.badoo.ribs.tutorials.tutorial6.rib.option_selector.OptionSelector

@GreetingsContainerScope
@dagger.Component(
    modules = [GreetingsContainerModule::class],
    dependencies = [
        GreetingsContainer.Dependency::class
    ]
)
internal interface GreetingsContainerComponent :
    HelloWorld.Dependency,
    OptionSelector.Dependency {

    @dagger.Component.Builder
    interface Builder {

        fun dependency(component: GreetingsContainer.Dependency): Builder

        fun build(): GreetingsContainerComponent
    }

    fun node(): Node<Nothing>
}
