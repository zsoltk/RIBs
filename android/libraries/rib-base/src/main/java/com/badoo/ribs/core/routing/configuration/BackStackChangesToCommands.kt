package com.badoo.ribs.core.routing.configuration

import android.os.Parcelable
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeature
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeatureState
import com.badoo.ribs.core.routing.configuration.feature.TransitionDescriptor
import io.reactivex.Observable
import io.reactivex.ObservableSource

/**
 * Takes the state emissions from [BackStackFeature], and translates them to a stream of
 * [ConfigurationCommand]s.
 *
 * @see [ConfigurationCommandCreator.diff]
 */
internal fun <C : Parcelable> ObservableSource<BackStackFeatureState<C>>.toCommands(): Observable<Transaction<C>> {
    return Observable.wrap(this)
        // FIXME this was the original and working one, but signature change means initialState is not accessible
//        .startWith(initialState)

        // FIXME temp solution, but this won't be good when restoring from bundle
        .startWith(BackStackFeatureState()) // TODO reconsider // Bootstrapper can overwrite it by the time we receive the first state emission here

        .buffer(2, 1)
        .flatMap { (previous, current) ->
            val commands =
                ConfigurationCommandCreator.diff(
                    previous.backStack,
                    current.backStack
                )
            when {
                commands.isNotEmpty() -> Observable.just(
                    Transaction.ListOfCommands(
                        descriptor = TransitionDescriptor(
                            from = previous,
                            to = current
                        ),
                        commands = commands
                    )
                )
                else -> Observable.empty()
            }
        }
}
