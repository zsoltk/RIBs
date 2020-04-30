package com.badoo.ribs.core

import com.badoo.ribs.core.view.ConceptView
import java.util.UUID

interface Concept<V : ConceptView> {

    val node: Node<V>

    data class Identifier(
        val uuid: UUID
    ) : Identifiable {

        override val id: String
            get() = uuid.toString()

        companion object {
            internal const val KEY_UUID = "concept.uuid"
        }
    }
}
