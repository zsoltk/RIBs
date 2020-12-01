package com.badoo.ribs.sandbox.migration_demo.container

import android.os.Parcelable
import com.badoo.ribs.core.Router
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.core.routing.transition.handler.TransitionHandler
import com.badoo.ribs.sandbox.migration_demo.container.ContainerRouter.Configuration
import com.badoo.ribs.sandbox.migration_demo.container.ContainerRouter.Configuration.Content
import com.badoo.ribs.sandbox.migration_demo.container.ContainerRouter.Configuration.Overlay
import com.badoo.ribs.sandbox.migration_demo.container.ContainerRouter.Configuration.Permanent
import kotlinx.android.parcel.Parcelize

class ContainerRouter(
    buildParams: BuildParams<*>,
    transitionHandler: TransitionHandler<Configuration>? = null
): Router<Configuration, Permanent, Content, Overlay, ContainerView>(
    buildParams = buildParams,
    transitionHandler = transitionHandler,
    initialConfiguration = Content.Default,
    permanentParts = emptyList()
) {
    sealed class Configuration : Parcelable {
        sealed class Permanent : Configuration()
        sealed class Content : Configuration() {
            @Parcelize object Default : Content()
        }
        sealed class Overlay : Configuration()
    }

    override fun resolveConfiguration(configuration: Configuration): RoutingAction =
        RoutingAction.noop()
}
