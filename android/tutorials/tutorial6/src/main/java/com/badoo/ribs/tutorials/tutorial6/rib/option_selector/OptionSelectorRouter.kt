package com.badoo.ribs.tutorials.tutorial6.rib.option_selector

import android.os.Parcelable
import com.badoo.ribs.core.Router
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.tutorials.tutorial6.rib.option_selector.OptionSelectorRouter.Configuration
import kotlinx.android.parcel.Parcelize

class OptionSelectorRouter: Router<Configuration, Nothing, Configuration, Nothing, OptionSelectorView>(
    initialConfiguration = Configuration.Default,
    permanentParts = emptyList()
) {
    sealed class Configuration : Parcelable {
        @Parcelize object Default : Configuration()
    }

    override fun resolveConfiguration(configuration: Configuration): RoutingAction<OptionSelectorView> =
        RoutingAction.noop()
}
