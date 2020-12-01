package com.badoo.ribs.sandbox.migration_demo.leaf

import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import io.reactivex.Single

class LeafNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ((ViewGroup) -> LeafView?)?,
    plugins: List<Plugin> = emptyList()
) : Node<LeafView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), Leaf {

    /**
     * TODO:
     *  - use router / input / output / feature for Leaf method implementations
     *  - keep in mind that in most cases you probably don't need to use interactor reference directly
     *      - its lifecycle methods are not accessible publicly (and it's good this way)
     *      - its internal consumers are usually reacting to children, and then it's better to
     *          trigger child workflows instead of faking them directly on the parent
     *  - as a general advice, try to trigger actions at points that are closest to where they would happen naturally,
     *      such that triggering involves executing all related actions (analytics, logging, etc)
     */

    override fun businessLogicOperation(): Single<Leaf> =
        executeWorkflow {
            // todo e.g. push wish to feature / trigger input / output
            // feature.accept()
        }

    // todo: expose ALL possible children (even permanent parts), or remove if there's none
    // override fun attachChild1(): Single<Child> =
    //     attachWorkflow {
    //         // router.push(ConfigurationForChild)
    //     }
}
