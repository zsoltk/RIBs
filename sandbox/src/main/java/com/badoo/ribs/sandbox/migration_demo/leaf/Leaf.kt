package com.badoo.ribs.sandbox.migration_demo.leaf

import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.customisation.RibCustomisation
import com.badoo.ribs.sandbox.migration_demo.leaf.Leaf.Input
import com.badoo.ribs.sandbox.migration_demo.leaf.Leaf.Output
import io.reactivex.Single

interface Leaf : Rib, Connectable<Input, Output> {

    interface Dependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: LeafView.Factory = LeafViewImpl.Factory()
    ) : RibCustomisation

    // Workflow
    // todo: do not delete - rename, and add more
    // todo: expose all meaningful operations
    fun businessLogicOperation(): Single<Leaf>

    // todo: expose all possible children (even permanent parts), or remove if there's none
    // fun attachChild1(): Single<Child>
}
