package com.badoo.ribs.sandbox.migration_demo.container

import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.routing.transition.handler.TransitionHandler
import com.badoo.ribs.customisation.RibCustomisation
import com.badoo.ribs.sandbox.migration_demo.container.Container.Input
import com.badoo.ribs.sandbox.migration_demo.container.Container.Output
import com.badoo.ribs.sandbox.migration_demo.container.routing.ContainerRouter
import io.reactivex.Single

interface Container : Rib, Connectable<Input, Output> {

    interface Dependency

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
