package com.badoo.ribs.example.rib.blocker

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder


class BlockerBuilder(
    private val dependency: Blocker.Dependency
) : SimpleBuilder<Blocker>() {

    override fun build(buildParams: BuildParams<Nothing?>): Blocker {
        val customisation = buildParams.getOrDefault(Blocker.Customisation())
        val interactor = BlockerInteractor(
            buildParams = buildParams,
            output = dependency.blockerOutput()
        )

        return object : Blocker {
            override val node: Node<BlockerView> = Node(
                buildParams = buildParams,
                viewFactory = customisation.viewFactory(null),
                router = null,
                interactor = interactor
            )
        }
    }
}
