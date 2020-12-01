package com.badoo.ribs.sandbox.migration_demo.container.routing

import com.badoo.ribs.sandbox.migration_demo.container.Container

internal class ContainerChildBuilders(
    dependency: Container.Dependency
) {
    private val subtreeDeps = SubtreeDependency(dependency)

    // TODO add public fields for all children
    // val child1 = Child1Builder(subtreeDeps)

    class SubtreeDependency(
        dependency: Container.Dependency
    ) : Container.Dependency by dependency
        // TODO enumerate dependencies of children this Rib can host
        // , Child1.Dependency
    {
        // TODO implement subtree dependencies here
    }
}



