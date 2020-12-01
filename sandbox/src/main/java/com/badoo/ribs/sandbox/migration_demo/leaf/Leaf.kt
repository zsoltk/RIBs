package com.badoo.ribs.sandbox.migration_demo.leaf

import com.badoo.ribs.core.Rib
import com.badoo.ribs.customisation.RibCustomisation
import io.reactivex.ObservableSource
import io.reactivex.Single
import io.reactivex.functions.Consumer

interface Leaf : Rib {

    interface Dependency {
        fun leafInput(): ObservableSource<Input>
        fun leafOutput(): Consumer<Output>
    }

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
