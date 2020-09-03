package com.badoo.ribs.sandbox.rib.switcher

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.source.tabcontroller.TabController
import com.badoo.ribs.sandbox.BuildConfig
import com.badoo.ribs.sandbox.rib.switcher.dialog.DialogToTestOverlay
import com.badoo.ribs.sandbox.rib.switcher.routing.SwitcherChildBuilders
import com.badoo.ribs.sandbox.rib.switcher.routing.SwitcherRouter
import com.badoo.ribs.sandbox.rib.switcher.routing.SwitcherRouter.Configuration
import com.badoo.ribs.sandbox.rib.switcher.routing.SwitcherRouter.Configuration.Content
import com.badoo.ribs.sandbox.util.CoffeeMachine
import com.badoo.ribs.sandbox.util.StupidCoffeeMachine

class SwitcherBuilder(
    private val dependency: Switcher.Dependency
) : SimpleBuilder<SwitcherNode>() {

    private val builders by lazy { SwitcherChildBuilders(dependency) }
    private val dialogToTestOverlay = DialogToTestOverlay()
    private val viewDependency: SwitcherView.Dependency =
        object : SwitcherView.Dependency {
            override fun coffeeMachine(): CoffeeMachine = StupidCoffeeMachine()
        }

    override fun build(buildParams: BuildParams<Nothing?>): SwitcherNode {
        val customisation = buildParams.getOrDefault(Switcher.Customisation())
        val tabController  = tabController(buildParams)
        val interactor = interactor(buildParams, tabController)
        val router = router(buildParams, customisation, tabController)

        return SwitcherNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(viewDependency),
            tabController = tabController,
            plugins = listOfNotNull(
                router,
                interactor,
                SwitcherDebugControls().takeIf { BuildConfig.DEBUG }
            )
        )
    }

    private fun tabController(buildParams: BuildParams<Nothing?>): TabController<Configuration> =
        TabController(
            buildParams = buildParams,
            configurations = listOf(Content.Hello, Content.Foo, Content.DialogsExample),
            active = Content.Foo
        )

    private fun interactor(
        buildParams: BuildParams<Nothing?>,
        tabController: TabController<Configuration>
    ): SwitcherInteractor =
        SwitcherInteractor(
            buildParams = buildParams,
            tabController = tabController,
            dialogToTestOverlay = dialogToTestOverlay
        )

    private fun router(
        buildParams: BuildParams<Nothing?>,
        customisation: Switcher.Customisation,
        routingSource: RoutingSource<Configuration>
    ): SwitcherRouter =
        SwitcherRouter(
            buildParams = buildParams,
            routingSource = routingSource,
            transitionHandler = customisation.transitionHandler,
            builders = builders,
            dialogLauncher = dependency.dialogLauncher(),
            dialogToTestOverlay = dialogToTestOverlay
        )
}
