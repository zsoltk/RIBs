package com.badoo.ribs.core.routing

import android.os.Parcelable
import com.badoo.ribs.core.routing.configuration.feature.RoutingElement
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeatureState
import io.reactivex.ObservableSource

interface RoutingSource<C : Parcelable> : ObservableSource<BackStackFeatureState<C>> {

    fun remove(identifier: RoutingElement.Identifier)
}
