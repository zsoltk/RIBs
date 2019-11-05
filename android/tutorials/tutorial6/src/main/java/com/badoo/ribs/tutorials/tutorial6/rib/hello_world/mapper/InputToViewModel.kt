package com.badoo.ribs.tutorials.tutorial6.rib.hello_world.mapper

import com.badoo.ribs.tutorials.tutorial6.R
import com.badoo.ribs.tutorials.tutorial6.rib.hello_world.HelloWorld
import com.badoo.ribs.tutorials.tutorial6.rib.hello_world.HelloWorld.Input.UpdateButtonText
import com.badoo.ribs.tutorials.tutorial6.rib.hello_world.HelloWorldView.ViewModel
import com.badoo.ribs.tutorials.tutorial6.util.Lexem
import com.badoo.ribs.tutorials.tutorial6.util.User

class InputToViewModel(
    private val user: User,
    private val config: HelloWorld.Configuration
) : (HelloWorld.Input) -> ViewModel? {

    override fun invoke(input: HelloWorld.Input): ViewModel? =
        when (input) {
            is UpdateButtonText -> ViewModel(
                titleText = Lexem.Resource(R.string.hello_world_title, user.name()),
                welcomeText = config.welcomeMessage,
                buttonText = input.text

            )
        }
}
