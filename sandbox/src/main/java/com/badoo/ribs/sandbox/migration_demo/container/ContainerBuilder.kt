@file:SuppressWarnings("LongParameterList")

package com.badoo.ribs.sandbox.migration_demo.container

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder
import com.badoo.ribs.core.routing.RoutingSource
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeature
import com.badoo.ribs.sandbox.migration_demo.container.feature.ContainerFeature
import com.badoo.ribs.sandbox.migration_demo.container.routing.ContainerChildBuilders
import com.badoo.ribs.sandbox.migration_demo.container.routing.ContainerRouter
import com.badoo.ribs.sandbox.migration_demo.container.routing.ContainerRouter.Configuration

class ContainerBuilder(
    private val dependency: Container.Dependency
) : SimpleBuilder<Container>() {

    override fun build(buildParams: BuildParams<Nothing?>): Container {
        val connections = ContainerChildBuilders(dependency)
        val customisation = buildParams.getOrDefault(Container.Customisation())
        val backStack = backStack(buildParams)
        val feature = feature()
        val interactor = interactor(buildParams, backStack, feature)
        val router = router(buildParams, backStack, connections, customisation)

        return node(buildParams, customisation, interactor, router)
    }

    private fun backStack(buildParams: BuildParams<*>) =
        BackStackFeature<Configuration>(
            buildParams = buildParams,
            initialConfiguration = Configuration.Content.Default
        )

    private fun feature() =
        ContainerFeature()

    private fun interactor(
        buildParams: BuildParams<*>,
        backStack: BackStackFeature<Configuration>,
        feature: ContainerFeature
    ) = ContainerInteractor(
        buildParams = buildParams,
        backStack = backStack,
        feature = feature
    )

    private fun router(
        buildParams: BuildParams<*>,
        routingSource: RoutingSource<Configuration>,
        builders: ContainerChildBuilders,
        customisation: Container.Customisation
    ) =
        ContainerRouter(
            buildParams = buildParams,
            builders = builders,
            routingSource = routingSource,
            transitionHandler = customisation.transitionHandler
        )

    private fun node(
        buildParams: BuildParams<*>,
        customisation: Container.Customisation,
        interactor: ContainerInteractor,
        router: ContainerRouter
    ) = ContainerNode(
        buildParams = buildParams,
        viewFactory = customisation.viewFactory(null),
        plugins = listOf(interactor, router)
    )
}
