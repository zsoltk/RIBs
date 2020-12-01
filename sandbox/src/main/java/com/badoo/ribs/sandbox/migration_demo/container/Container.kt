package com.badoo.ribs.sandbox.migration_demo.container

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.routing.transition.handler.TransitionHandler
import com.badoo.ribs.customisation.RibCustomisation
import io.reactivex.ObservableSource
import io.reactivex.Single
import io.reactivex.functions.Consumer

interface Container : Rib {

    interface Dependency {
        fun containerInput(): ObservableSource<Input>
        fun containerOutput(): Consumer<Output>
    }

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: ContainerView.Factory = ContainerViewImpl.Factory(),
        val transitionHandler: TransitionHandler<ContainerRouter.Configuration>? = null
    ) : RibCustomisation

    // Workflow
    // todo: do not delete - rename, and add more
    // todo: expose all meaningful operations
    fun businessLogicOperation(): Single<Container>

    // todo: expose all possible children (even permanent parts), or remove if there's none
    // fun attachChild1(): Single<Child>
}
