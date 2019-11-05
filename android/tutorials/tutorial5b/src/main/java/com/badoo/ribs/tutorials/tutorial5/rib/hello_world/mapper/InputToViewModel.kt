package com.badoo.ribs.tutorials.tutorial5.rib.hello_world.mapper

import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorld
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorld.Input.UpdateButtonText
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorldView.ViewModel
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.viewmodel.ViewModelFactory

class InputToViewModel(
    private val viewModelFactory: ViewModelFactory
) : (HelloWorld.Input) -> ViewModel? {

    override fun invoke(input: HelloWorld.Input): ViewModel? =
        when (input) {
            is UpdateButtonText -> viewModelFactory.viewModel(input.text)
        }
}

class InputToViewModel: (HelloWorld.Input) -> ViewModel? {

    override fun invoke(input: HelloWorld.Input): ViewModel? =
        when (input) {
            is UpdateButtonText -> ViewModelFactory.viewModel(input.text)
        }
}
