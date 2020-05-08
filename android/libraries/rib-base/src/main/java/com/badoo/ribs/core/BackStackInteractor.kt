/*
 * Copyright (C) 2017. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.badoo.ribs.core

import android.os.Parcelable
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.plugin.BackPressHandler
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.plugin.RibLifecycleAware
import com.badoo.ribs.core.plugin.ViewAware
import com.badoo.ribs.core.routing.RoutingSource
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeature
import com.badoo.ribs.core.view.RibView

/**
 * The base implementation for all [BackStackInteractor]s.
 *
 * @param <C> the type of Configuration this Interactor can expect to push to its [Router].
 * @param <V> the type of [RibView].
 **/
abstract class BackStackInteractor<C : Parcelable, V : RibView>(
    val backStack: BackStackFeature<C>
) : Interactor<V>(
    backPressHandler = backStack
), RoutingSource<C> by backStack {

    constructor(
        buildParams: BuildParams<*>,
        initialConfiguration: C
    ) : this(
        BackStackFeature(
            initialConfiguration = initialConfiguration,
            buildParams = buildParams
        )
    )
}
