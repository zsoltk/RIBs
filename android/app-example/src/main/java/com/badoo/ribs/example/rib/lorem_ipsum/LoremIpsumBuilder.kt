package com.badoo.ribs.example.rib.lorem_ipsum

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder

class LoremIpsumBuilder(
    private val dependency: LoremIpsum.Dependency
) : SimpleBuilder<LoremIpsum>() {

    override fun build(buildParams: BuildParams<Nothing?>): LoremIpsum {
        val customisation = buildParams.getOrDefault(LoremIpsum.Customisation())
        val interactor = LoremIpsumInteractor(
            buildParams = buildParams,
            output = dependency.loremIpsumOutput()
        )

        return object : LoremIpsum {
            override val node: Node<LoremIpsumView> = Node(
                buildParams = buildParams,
                viewFactory = customisation.viewFactory(null),
                router = null,
                interactor = interactor
            )
        }
    }
}
