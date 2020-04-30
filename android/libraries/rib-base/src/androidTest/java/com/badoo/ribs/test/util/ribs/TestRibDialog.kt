package com.badoo.ribs.test.util.ribs

import com.badoo.ribs.core.builder.ConceptFactory
import com.badoo.ribs.dialog.Dialog


class TestRibDialog(
    conceptFactory: ConceptFactory
) : Dialog<Dialog.Event>({
    nodeFactory(conceptFactory = conceptFactory)
})
