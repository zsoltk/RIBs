package com.badoo.ribs.tutorials.tutorial5.rib.hello_world

import android.arch.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Router
import com.badoo.ribs.tutorials.tutorial5.R
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorldRouter.Configuration
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.HelloWorldRouter.Configuration.Content
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.mapper.InputToViewModel
import com.badoo.ribs.tutorials.tutorial5.rib.hello_world.mapper.ViewEventToOutput
import com.badoo.ribs.tutorials.tutorial5.util.Lexem
import com.badoo.ribs.tutorials.tutorial5.util.User
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

class HelloWorldInteractor(
    private val user: User,
    private val config: HelloWorld.Configuration,
    private val input: ObservableSource<HelloWorld.Input>,
    private val output: Consumer<HelloWorld.Output>,
    router: Router<Configuration, Nothing, Content, Nothing, HelloWorldView>
) : Interactor<Configuration, Content, Nothing, HelloWorldView>(
    router = router,
    disposables = null
) {
    private val inputToViewModel = InputToViewModel(user, config)

    override fun onViewCreated(view: HelloWorldView, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)
        setInitialViewModel(view)
        viewLifecycle.createDestroy {
            bind(view to output using ViewEventToOutput)
            bind(input to view using inputToViewModel)
        }
    }

    private fun setInitialViewModel(view: HelloWorldView) {
        view.accept(
            HelloWorldView.ViewModel(
                titleText = Lexem.Resource(R.string.hello_world_title, user.name()),
                welcomeText = config.welcomeMessage,
                buttonText = Lexem.Resource(R.string.hello_world_button_text)
            )
        )
    }
}
