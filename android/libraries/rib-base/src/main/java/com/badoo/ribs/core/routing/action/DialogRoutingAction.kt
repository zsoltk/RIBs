package com.badoo.ribs.core.routing.action

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.builder.BuildContext
import com.badoo.ribs.core.routing.RoutingSource
import com.badoo.ribs.core.routing.configuration.feature.RoutingElement
import com.badoo.ribs.dialog.Dialog
import com.badoo.ribs.dialog.DialogLauncher

class DialogRoutingAction<Event : Any>(
    private val routingSource: RoutingSource<*>,
    private val routingElement: RoutingElement<*>,
    private val dialogLauncher: DialogLauncher,
    private val dialog: Dialog<Event>
) : RoutingAction {

    override val nbNodesToBuild: Int = 1

    override fun buildNodes(buildContexts: List<BuildContext>) : List<Rib> =
        dialog.buildNodes(buildContexts.first())

    override fun execute() {
        dialogLauncher.show(dialog, onClose = {
            routingSource.remove(routingElement)
        })
    }

    override fun cleanup() {
        dialogLauncher.hide(dialog)
    }

    companion object {
        fun showDialog(
            routingSource: RoutingSource<*>,
            routingElement: RoutingElement<*>,
            dialogLauncher: DialogLauncher,
            dialog: Dialog<*>
        ): RoutingAction =
            DialogRoutingAction(routingSource, routingElement, dialogLauncher, dialog)
    }
}
