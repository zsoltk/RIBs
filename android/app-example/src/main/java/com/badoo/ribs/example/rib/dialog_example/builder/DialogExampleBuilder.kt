package com.badoo.ribs.example.rib.dialog_example.builder

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder
import com.badoo.ribs.example.rib.dialog_example.DialogExample
import com.badoo.ribs.example.rib.dialog_example.DialogExampleNode

class DialogExampleBuilder(
    private val dependency: DialogExample.Dependency
) : SimpleBuilder<DialogExampleNode>() {

    override fun build(buildParams: BuildParams<Nothing?>): DialogExampleNode =
        DaggerDialogExampleComponent
            .factory()
            .create(
                dependency = dependency,
                customisation = buildParams.getOrDefault(DialogExample.Customisation()),
                buildParams = buildParams
            )
            .node()
}
