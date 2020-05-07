package com.badoo.ribs.example.rib.switcher

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.RoutingSource
import com.badoo.ribs.core.routing.configuration.feature.operation.pop
import com.badoo.ribs.core.routing.configuration.feature.operation.push
import com.badoo.ribs.core.routing.configuration.feature.operation.pushOverlay
import com.badoo.ribs.dialog.Dialog
import com.badoo.ribs.example.rib.blocker.Blocker
import com.badoo.ribs.example.rib.menu.Menu
import com.badoo.ribs.example.rib.switcher.SwitcherView.Event
import com.badoo.ribs.example.rib.switcher.dialog.DialogToTestOverlay
import com.badoo.ribs.example.rib.switcher.subtree.Configuration
import com.badoo.ribs.example.rib.switcher.subtree.Configuration.Content
import com.badoo.ribs.example.rib.switcher.subtree.Configuration.Overlay
import io.reactivex.functions.Consumer

class SwitcherInteractor(
    buildParams: BuildParams<Nothing?>,
    private val backStack: SwitcherBackStack,
    private val dialogToTestOverlay: DialogToTestOverlay
) : Interactor<SwitcherView>(
    buildParams = buildParams,
    backPressHandler = backStack
), RoutingSource<Configuration> by backStack {

    override fun onViewCreated(view: SwitcherView, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)
        viewLifecycle.startStop {
            bind(view to viewEventConsumer)
            bind(dialogToTestOverlay to dialogEventConsumer)
        }
    }

    internal inner class MenuListener : Consumer<Menu.Output> {
        override fun accept(output: Menu.Output) =
            when (output) {
              is Menu.Output.MenuItemSelected -> when (output.menuItem) {
                Menu.MenuItem.FooBar -> {
                    backStack.push(Content.Foo)
                }
                Menu.MenuItem.HelloWorld -> {
                    backStack.push(Content.Hello)
                }
                Menu.MenuItem.Dialogs -> {
                    backStack.push(Content.DialogsExample)
                }
            }
        }
    }

    private val viewEventConsumer: Consumer<Event> = Consumer {
        when (it) {
            Event.ShowOverlayDialogClicked -> backStack.pushOverlay(Overlay.Dialog)
            Event.ShowBlockerClicked -> backStack.push(Content.Blocker)
        }
    }

    internal val loremIpsumOutputConsumer: Consumer<Blocker.Output> = Consumer {
        // Clear Blocker
        backStack.pop()
    }

    private val dialogEventConsumer : Consumer<Dialog.Event> = Consumer {
        when (it) {
            Dialog.Event.Positive -> {
                // do something if you want
            }
        }
    }
}
