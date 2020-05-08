package com.badoo.ribs.example.rib.switcher.subtree

import com.badoo.ribs.core.Router
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.action.AttachRibRoutingAction.Companion.attach
import com.badoo.ribs.core.routing.action.CompositeRoutingAction.Companion.composite
import com.badoo.ribs.core.routing.action.DialogRoutingAction.Companion.showDialog
import com.badoo.ribs.core.routing.action.InvokeOnExecute.Companion.execute
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.core.routing.transition.handler.TransitionHandler
import com.badoo.ribs.dialog.DialogLauncher
import com.badoo.ribs.example.rib.blocker.BlockerBuilder
import com.badoo.ribs.example.rib.dialog_example.builder.DialogExampleBuilder
import com.badoo.ribs.example.rib.foo_bar.FooBarBuilder
import com.badoo.ribs.example.rib.hello_world.HelloWorldBuilder
import com.badoo.ribs.example.rib.menu.Menu
import com.badoo.ribs.example.rib.menu.Menu.Input.SelectMenuItem
import com.badoo.ribs.example.rib.menu.Menu.MenuItem
import com.badoo.ribs.example.rib.menu.MenuBuilder
import com.badoo.ribs.example.rib.switcher.SwitcherInteractor
import com.badoo.ribs.example.rib.switcher.dialog.DialogToTestOverlay
import com.badoo.ribs.example.rib.switcher.subtree.Configuration.Content
import com.badoo.ribs.example.rib.switcher.subtree.Configuration.Overlay
import com.badoo.ribs.example.rib.switcher.subtree.Configuration.Permanent
import com.jakewharton.rxrelay2.PublishRelay

class SwitcherRouter(
    interactor: SwitcherInteractor,
    transitionHandler: TransitionHandler<Configuration>,
    private val fooBarBuilder: FooBarBuilder,
    private val helloWorldBuilder: HelloWorldBuilder,
    private val dialogExampleBuilder: DialogExampleBuilder,
    private val blockerBuilder: BlockerBuilder,
    private val menuBuilder: MenuBuilder,
    private val dialogLauncher: DialogLauncher,
    private val dialogToTestOverlay: DialogToTestOverlay
): Router<Configuration, Permanent>(
    transitionHandler = transitionHandler,
    routingSource = interactor,
    permanentParts = listOf(
        Permanent.Menu
    )
) {

    internal val menuUpdater = PublishRelay.create<Menu.Input>()

    override fun resolveConfiguration(configuration: Configuration): RoutingAction =
        when (configuration) {
            is Permanent.Menu -> attach { menuBuilder.build(it) }
            is Content.Hello -> composite(
                attach { helloWorldBuilder.build(it) },
                execute { menuUpdater.accept(SelectMenuItem(MenuItem.HelloWorld)) }
            )
            is Content.Foo -> composite(
                attach { fooBarBuilder.build(it) },
                execute { menuUpdater.accept(SelectMenuItem(MenuItem.FooBar)) }
            )
            is Content.DialogsExample -> composite(
                attach { dialogExampleBuilder.build(it) },
                execute { menuUpdater.accept(SelectMenuItem(MenuItem.Dialogs)) }
            )
            is Content.Blocker -> attach { blockerBuilder.build(it) }
            is Overlay.Dialog -> showDialog(this, dialogLauncher, dialogToTestOverlay)
        }
}
