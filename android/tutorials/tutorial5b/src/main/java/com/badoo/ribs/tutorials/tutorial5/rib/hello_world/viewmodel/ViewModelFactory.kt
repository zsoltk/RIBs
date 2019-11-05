package com.badoo.ribs.tutorials.tutorial5.rib.hello_world.viewmodel

import com.badoo.ribs.tutorials.tutorial5.R
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorld
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorldView.ViewModel
import com.badoo.ribs.tutorials.tutorial5.util.Lexem
import com.badoo.ribs.tutorials.tutorial5.util.User

class ViewModelFactory(
    private val user: User,
    private val config: HelloWorld.Config
) {
    val initial = viewModel(config.buttonText)

    fun viewModel(buttonText: Lexem): ViewModel = ViewModel(
        titleText = Lexem.Resource(R.string.hello_world_title, user.name()),
        welcomeText = config.welcomeMessage,
        buttonText = buttonText
    )
}
