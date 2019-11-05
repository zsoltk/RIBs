package com.badoo.ribs.tutorials.tutorial7.rib.greetings_container

import android.os.Parcelable
import com.badoo.ribs.core.Router
import com.badoo.ribs.core.routing.action.AttachRibRoutingAction.Companion.attach
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.tutorials.tutorial7.rib.greetings_container.GreetingsContainerRouter.Configuration
import com.badoo.ribs.tutorials.tutorial7.rib.hello_world.builder.HelloWorldBuilder
import com.badoo.ribs.tutorials.tutorial7.rib.option_selector.builder.OptionSelectorBuilder
import kotlinx.android.parcel.Parcelize

class GreetingsContainerRouter(
    private val helloWorldBuilder: HelloWorldBuilder,
    private val optionSelectorBuilder: OptionSelectorBuilder
): Router<Configuration, Nothing, Configuration, Nothing, Nothing>(
    initialConfiguration = Configuration.HelloWorld
) {
    sealed class Configuration : Parcelable {
        @Parcelize object HelloWorld : Configuration()
        @Parcelize data class MoreOptions(val selectionIndex: Int) : Configuration()
    }

    override fun resolveConfiguration(configuration: Configuration): RoutingAction<Nothing> =
        when (configuration) {
            is Configuration.HelloWorld -> attach { helloWorldBuilder.build() }
            is Configuration.MoreOptions -> attach { optionSelectorBuilder.build(configuration.selectionIndex) }
        }
}
