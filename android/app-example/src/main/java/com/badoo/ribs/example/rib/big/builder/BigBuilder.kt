package com.badoo.ribs.example.rib.big.builder

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder

import com.badoo.ribs.example.rib.big.Big
import com.badoo.ribs.example.rib.big.BigNode

class BigBuilder(
    private val dependency: Big.Dependency
) : SimpleBuilder<BigNode>() {

    override fun build(buildParams: BuildParams<Nothing?>): BigNode =
        DaggerBigComponent
            .factory()
            .create(
                dependency = dependency,
                customisation = buildParams.getOrDefault(Big.Customisation()),
                buildParams = buildParams
            )
            .node()
}
