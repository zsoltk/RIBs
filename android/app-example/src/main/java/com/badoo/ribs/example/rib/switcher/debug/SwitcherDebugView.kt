package com.badoo.ribs.example.rib.switcher.debug

import android.view.View
import android.widget.Button
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.plugin.DebugControls
import com.badoo.ribs.customisation.inflate
import com.badoo.ribs.example.R
import com.badoo.ribs.example.rib.switcher.Switcher
import com.badoo.ribs.example.rib.switcher.SwitcherView
import io.reactivex.disposables.CompositeDisposable

class SwitcherDebugControls(
    private val disposables: CompositeDisposable = CompositeDisposable(),
    isEnabled: Boolean = true
) : DebugControls(
    viewFactory = { inflate(it, R.layout.debug_switcher) },
    isEnabled = isEnabled
//) : DisposablePlugin { // TODO
)  {

    private lateinit var switcher: Switcher

    override fun init(node: Node<*>) {
        super.init(node)
        switcher = node as Switcher
    }

    override fun onDebugViewCreated(debugView: View) {
        debugView.findViewById<Button>(R.id.workflow_1).setOnClickListener {
            disposables.add(
                // Trigger workflow
                switcher.attachHelloWorld().subscribe()
            )
        }

        debugView.findViewById<Button>(R.id.workflow_2).setOnClickListener {
            disposables.add(
                // Trigger workflow
                switcher.attachFooBar().subscribe()
            )
        }
    }
}
