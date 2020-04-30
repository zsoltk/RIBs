package com.badoo.ribs.core.routing.action

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.ConceptFactory

class AnchoredAttachRoutingAction(
    private val anchor: Node<*>,
    conceptFactory: ConceptFactory
) : AttachRibRoutingAction(
    conceptFactory = conceptFactory
) {

    override fun anchor(): Node<*>? =
        anchor

    companion object {
        fun anchor(anchor: Node<*>, conceptFactory: ConceptFactory): RoutingAction =
                AnchoredAttachRoutingAction(
                    anchor = anchor,
                    conceptFactory = conceptFactory
                )
    }
}
