package com.badoo.ribs.core.builder

import com.badoo.ribs.core.Concept

typealias ConceptFactory = (buildContext: BuildContext) -> Concept<*>

