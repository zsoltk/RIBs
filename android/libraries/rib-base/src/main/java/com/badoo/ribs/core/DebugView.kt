package com.badoo.ribs.core

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.customisation.inflate
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class DebugView<T : RibView>(
    private val node: Node<T>,
    @LayoutRes private val layoutResId: Int,
    private val bindings: Map<Int, Single<*>> = mapOf()
) : Plugin<T> {

    // This should be parent's debug view
    private val targetParentViewGroup: ViewGroup = TODO()

    // This is probably not a good idea
//    init {
//        node.addPlugin(this)
//    }

    private var view: View? = null

    private val disposable = CompositeDisposable()

    override fun onAttachToView(parentViewGroup: ViewGroup) {
        super.onAttachToView(parentViewGroup)
        createView(parentViewGroup).let {
            // TODO create vertical programmatically
            //  + add it
            //  + add host for children
            //  + set view to this
            // LinearLayout()

            view = it
            onViewCreated(it)
        }
        targetParentViewGroup.addView(view)
    }

    override fun onDetachFromView(parentViewGroup: ViewGroup) {
        super.onDetachFromView(parentViewGroup)
        view?.let {
            targetParentViewGroup.removeView(it)
            onDestroyView(it)
        }
        disposable.dispose()
        this.view = null
    }

    fun createView(parentViewGroup: ViewGroup): View {
        inflate<View>(parentViewGroup, layoutResId).let {
            bindings.entries.forEach { (viewId, workflowOperation) ->
                it.findViewById<View>(viewId).setOnClickListener {
                    disposable.add(workflowOperation.subscribe())
                }
            }

            return it
        }
    }

    override fun onAttachChildView(child: Node<*>) {
        super.onAttachChildView(child)
        // TODO add child.debugView
    }

    override fun onDetachChildView(child: Node<*>) {
        super.onDetachChildView(child)
        // TODO remove child.debugView
    }

    open fun onViewCreated(view: View) {
        // set further listeners in descendant classes if needed
    }

    open fun onDestroyView(view: View) {
        // client clean up
    }
}
