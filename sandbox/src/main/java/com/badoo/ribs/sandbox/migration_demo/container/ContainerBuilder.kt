@file:SuppressWarnings("LongParameterList")

package com.badoo.ribs.sandbox.migration_demo.container

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder
import com.badoo.ribs.sandbox.migration_demo.container.feature.ContainerFeature

class ContainerBuilder(
    private val dependency: Container.Dependency
) : SimpleBuilder<Container>() {

    override fun build(buildParams: BuildParams<Nothing?>): Container {
        val customisation = buildParams.getOrDefault(Container.Customisation())
        val router = router(buildParams, customisation)
        val feature = feature()
        val interactor = interactor(dependency, buildParams, router, feature)

        return node(buildParams, customisation, interactor, router)
    }

    private fun feature() =
        ContainerFeature()

    private fun router(
        buildParams: BuildParams<*>,
        customisation: Container.Customisation
    ) =
        ContainerRouter(
            buildParams = buildParams,
            transitionHandler = customisation.transitionHandler
        )

    private fun interactor(
        dependency: Container.Dependency,
        buildParams: BuildParams<*>,
        router: ContainerRouter,
        feature: ContainerFeature
    ) = ContainerInteractor(
            buildParams = buildParams,
            router = router,
            input = dependency.containerInput(),
            output = dependency.containerOutput(),
            feature = feature
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
