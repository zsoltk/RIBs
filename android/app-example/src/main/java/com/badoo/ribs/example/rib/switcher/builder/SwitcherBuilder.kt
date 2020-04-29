package com.badoo.ribs.example.rib.switcher.builder

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder
import com.badoo.ribs.example.rib.switcher.Switcher
import com.badoo.ribs.example.rib.switcher.SwitcherNode

class SwitcherBuilder(
    private val dependency: Switcher.Dependency
) : SimpleBuilder<SwitcherNode>() {

    override fun build(buildParams: BuildParams<Nothing?>): SwitcherNode =
        DaggerSwitcherComponent
            .factory()
            .create(
                dependency = dependency,
                customisation = buildParams.getOrDefault(Switcher.Customisation()),
                buildParams = buildParams
            )
            .node()
}
