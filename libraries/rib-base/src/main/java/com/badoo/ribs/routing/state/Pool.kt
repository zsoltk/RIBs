package com.badoo.ribs.routing.state

import android.os.Parcelable
import com.badoo.ribs.routing.Routing

// Fixme map key needs to be Identifier, otherwise Meta change will mess it up
internal typealias Pool<C> = Map<Routing<C>, RoutingContext<C>>

internal typealias MutablePool<C> = MutableMap<Routing<C>, RoutingContext<C>>

internal fun <C : Parcelable> poolOf(): Pool<C> = mapOf()

internal fun <C : Parcelable> mutablePoolOf(): MutablePool<C> = mutableMapOf()

internal fun <C : Parcelable> Pool<C>.toMutablePool(): MutablePool<C> = toMutableMap()
