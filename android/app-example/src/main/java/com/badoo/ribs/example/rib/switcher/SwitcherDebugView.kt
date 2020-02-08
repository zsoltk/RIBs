package com.badoo.ribs.example.rib.switcher

import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import com.badoo.ribs.customisation.inflate
import com.badoo.ribs.example.R
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable


class SwitcherDebugView(
    workflow: Switcher.Workflow
) : DebugView(
    layoutResId = R.layout.rib_debug_switcher,
    bindings = hashMapOf(
        R.id.debug_1 to workflow.attachHelloWorld(),
        R.id.debug_2 to workflow.doSomethingAndStayOnThisNode()
    )
)

abstract class DebugView(
    @LayoutRes private val layoutResId: Int,
    private val bindings: Map<Int, Single<*>> = mapOf()
) {
    var view: View? = null
        private set

    private val disposable = CompositeDisposable()

    fun onCreateView(parentViewGroup: ViewGroup): View {
        inflate<View>(parentViewGroup, layoutResId).let {
            view = it

            bindings.entries.forEach { (viewId, workflowOperation) ->
                it.findViewById<View>(viewId).setOnClickListener {
                    disposable.add(workflowOperation.subscribe())
                }
            }

            return it
        }
    }

    fun onViewCreated() {
        // set further listeners in descendant classes if needed
    }

    @CallSuper
    open fun onDestroyView() {
        disposable.dispose()
        view = null
    }
}
