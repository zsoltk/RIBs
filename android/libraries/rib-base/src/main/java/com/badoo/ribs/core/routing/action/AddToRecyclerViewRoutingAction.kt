package com.badoo.ribs.core.routing.action

import com.badoo.ribs.core.AttachMode
import com.badoo.ribs.core.Concept
import com.badoo.ribs.core.builder.BuildContext
import com.badoo.ribs.core.builder.ConceptFactory

open class AddToRecyclerViewRoutingAction(
    private val conceptFactory: ConceptFactory
) : RoutingAction {

    override val nbConcepts: Int = 1

    override fun build(buildContexts: List<BuildContext>): List<Concept<*>> =
        listOf(
            conceptFactory.invoke(
                buildContexts.first().copy(
                    attachMode = AttachMode.EXTERNAL
                )
            )
        )

    companion object {
        fun recyclerView(conceptFactory: ConceptFactory): RoutingAction =
            AddToRecyclerViewRoutingAction(conceptFactory)
    }
}
