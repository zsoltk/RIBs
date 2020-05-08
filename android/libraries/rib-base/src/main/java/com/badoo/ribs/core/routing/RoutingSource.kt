package com.badoo.ribs.core.routing

import android.os.Parcelable
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeatureState
import io.reactivex.ObservableSource

// TODO consider back press handler
interface RoutingSource<C : Parcelable> : ObservableSource<BackStackFeatureState<C>>
