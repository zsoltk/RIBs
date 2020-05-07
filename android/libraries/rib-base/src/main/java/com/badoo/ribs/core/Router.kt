package com.badoo.ribs.core

import android.os.Parcelable
import com.badoo.ribs.core.routing.configuration.ConfigurationResolver
import com.badoo.ribs.core.view.RibView

//abstract class Router<C : Parcelable, Permanent : C, Content : C>(
abstract class Router<C : Parcelable>(
) : ConfigurationResolver<C> { // TODO so maybe ConfigurationResolver should be called Router and this one deleted

//    abstract val initialConfiguration: Content // TODO move this to BackStack / Subtree
//
//    abstract val permanentParts: List<Permanent> // TODO consider moving this to Subtree
}
