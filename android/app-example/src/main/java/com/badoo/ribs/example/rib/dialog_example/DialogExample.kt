package com.badoo.ribs.example.rib.dialog_example

import com.badoo.ribs.core.Concept
import com.badoo.ribs.customisation.RibCustomisation
import com.badoo.ribs.dialog.CanProvideDialogLauncher

interface DialogExample : Concept<DialogExampleView> {

    interface Dependency : CanProvideDialogLauncher

    class Customisation(
        val viewFactory: DialogExampleView.Factory = DialogExampleViewImpl.Factory()
    ) : RibCustomisation
}
