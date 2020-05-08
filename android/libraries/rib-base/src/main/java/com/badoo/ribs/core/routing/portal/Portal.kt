package com.badoo.ribs.core.routing.portal

import android.os.Parcelable
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.core.routing.transition.handler.TransitionHandler
import io.reactivex.Single

interface Portal : Rib {

    interface OtherSide {
        fun showContent(remoteNode: Node<*>, remoteConfiguration: Parcelable)
        fun showOverlay(remoteNode: Node<*>, remoteConfiguration: Parcelable)
    }

    interface Dependency {
        fun defaultRoutingAction(): (Portal.OtherSide) -> RoutingAction
        fun transitionHandler(): TransitionHandler<PortalRouter.Configuration>? = null
        fun plugins(): List<Plugin> = emptyList()
    }

    // Workflow
    fun showDefault(): Single<Rib>
    fun showInPortal(ancestryInfo: AncestryInfo): Single<Rib>
}
