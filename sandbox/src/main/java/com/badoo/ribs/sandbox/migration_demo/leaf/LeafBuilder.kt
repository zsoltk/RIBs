package com.badoo.ribs.sandbox.migration_demo.leaf

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder
import com.badoo.ribs.sandbox.migration_demo.leaf.feature.LeafFeature

class LeafBuilder(
    private val dependency: Leaf.Dependency
) : SimpleBuilder<Leaf>() {

    override fun build(buildParams: BuildParams<Nothing?>): Leaf {
        val customisation = buildParams.getOrDefault(Leaf.Customisation())
        val feature = feature()
        val interactor = interactor(buildParams, feature)

        return node(buildParams, customisation, interactor)
    }

    private fun feature() =
        LeafFeature()

    private fun interactor(
        buildParams: BuildParams<*>,
        feature: LeafFeature
    ) =
        LeafInteractor(
            buildParams = buildParams,
            feature = feature
        )

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: Leaf.Customisation,
        interactor: LeafInteractor
    ) =
        LeafNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = listOf(interactor)
        )
}
