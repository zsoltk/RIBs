package com.badoo.ribs.tutorials.tutorial5.rib.hello_world

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Router
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorldRouter.Configuration
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorldRouter.Configuration.Content
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.mapper.InputToViewModel
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.mapper.ViewEventToOutput
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.viewmodel.ViewModelFactory
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

class HelloWorldInteractor(
    private val inputToViewModel: InputToViewModel,
    private val viewModelFactory: ViewModelFactory,
    private val input: ObservableSource<HelloWorld.Input>,
    private val output: Consumer<HelloWorld.Output>,
    savedInstanceState: Bundle?,
    router: Router<Configuration, Nothing, Content, Nothing, HelloWorldView>
) : Interactor<Configuration, Content, Nothing, HelloWorldView>(
    savedInstanceState = savedInstanceState,
    router = router,
    disposables = null
) {

    override fun onViewCreated(view: HelloWorldView, viewLifecycle: Lifecycle) {
        view.accept(viewModelFactory.initial)
        viewLifecycle.startStop {
            bind(view to output using ViewEventToOutput)
            bind(input to view using inputToViewModel)
        }
    }
}
