package com.badoo.ribs.example.rib.switcher.debug

import android.view.View
import android.widget.Button
import com.badoo.ribs.core.plugin.DebugControls
import com.badoo.ribs.example.R
import com.badoo.ribs.example.rib.switcher.Switcher
import com.badoo.ribs.example.rib.switcher.SwitcherView
import io.reactivex.disposables.CompositeDisposable

class SwitcherDebugControls(
    private val switcher: Switcher,
    private val disposables: CompositeDisposable = CompositeDisposable()
) : DebugControls<SwitcherView>(
    node = switcher.node,
    viewFactory = TODO()
//) : DisposablePlugin { // TODO
)  {

    override fun onDebugViewCreated(debugView: View) {
        debugView.findViewById<Button>(R.id.blocker_button).setOnClickListener {
            disposables.add(
                // Trigger workflow
                switcher.attachHelloWorld().subscribe()
            )
        }
    }
}
