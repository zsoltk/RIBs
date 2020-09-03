package com.badoo.ribs.routing.source.tabcontroller

import android.os.Parcelable
import com.badoo.ribs.routing.history.RoutingHistory
import com.badoo.ribs.routing.history.RoutingHistoryElement
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

typealias Snapshot<C> = Set<RoutingHistoryElement<C>> // Just RoutingHistory would do fine, solve parcelize on it

@Parcelize
data class State<C : Parcelable>(
    val id: Int = Random.nextInt(),
    val history: List<Snapshot<C>> = emptyList(),
    val current: Snapshot<C> = emptySet()
) : Parcelable, RoutingHistory<C> {

    override fun iterator(): Iterator<RoutingHistoryElement<C>> =
        current.iterator()
}


