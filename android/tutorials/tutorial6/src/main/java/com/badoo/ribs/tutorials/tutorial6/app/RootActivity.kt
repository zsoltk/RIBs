package com.badoo.ribs.tutorials.tutorial6.app

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.ViewGroup
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Node
import com.badoo.ribs.tutorials.tutorial6.R
import com.badoo.ribs.tutorials.tutorial6.rib.greetings_container.GreetingsContainer
import com.badoo.ribs.tutorials.tutorial6.rib.greetings_container.builder.GreetingsContainerBuilder
import com.badoo.ribs.tutorials.tutorial6.util.User
import io.reactivex.functions.Consumer

/** The tutorial app's single activity */
class RootActivity : RibActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_root)
        super.onCreate(savedInstanceState)
    }

    override val rootViewGroup: ViewGroup
        get() = findViewById(R.id.root)

    override fun createRib(): Node<*> =
        GreetingsContainerBuilder(
            object : GreetingsContainer.Dependency {
                override fun user(): User =
                    User.DUMMY

                override fun greetingsContainerOutput(): Consumer<GreetingsContainer.Output> =
                    Consumer { output ->
                        when (output) {
                            is GreetingsContainer.Output.GreetingsSaid -> {
                                Snackbar.make(
                                    rootViewGroup,
                                    output.greeting.resolve(this@RootActivity),
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
            }
        ).build()

//    override fun createRib(): Node<*> =
//        MoreOptionsBuilder(
//            object : MoreOptions.Dependency {
//                override fun moreOptionsInput(): ObservableSource<MoreOptions.Input> = Observable.empty()
//
//                override fun moreOptionsOutput(): Consumer<MoreOptions.Output> = Consumer {
//                    Log.d("MoreOptions", it.toString())
//                }
//
//            }
//        ).build()
}
