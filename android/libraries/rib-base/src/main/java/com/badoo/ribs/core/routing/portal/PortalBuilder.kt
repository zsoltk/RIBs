package com.badoo.ribs.core.routing.portal

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder

class PortalBuilder(
    private val dependency: Portal.Dependency
) : SimpleBuilder<Portal>() {

    override fun build(buildParams: BuildParams<Nothing?>): Portal {
        val interactor = PortalInteractor(
            buildParams = buildParams
        )
        val router = PortalRouter(
            routingSource = interactor,
            defaultRoutingAction = dependency.defaultRoutingAction().invoke(interactor),
            transitionHandler = dependency.transitionHandler()
        )

        return PortalNode(
            buildParams = buildParams,
            plugins =
                listOf(
                    interactor,
                    router
                ) + dependency.plugins(),
            backStack = interactor.backStack
        )
    }
}
