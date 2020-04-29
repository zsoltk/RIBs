package com.badoo.ribs.template.node_dagger.foo_bar.builder

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder
import com.badoo.ribs.template.node_dagger.foo_bar.FooBar
import com.badoo.ribs.template.node_dagger.foo_bar.FooBarNode

class FooBarBuilder(
    private val dependency: FooBar.Dependency
) : SimpleBuilder<FooBarNode>() {

    override fun build(buildParams: BuildParams<Nothing?>): FooBarNode =
        DaggerFooBarComponent
            .factory()
            .create(
                dependency = dependency,
                customisation = buildParams.getOrDefault(FooBar.Customisation()),
                buildParams = buildParams
            )
            .node()
}
