package com.badoo.ribs.example.rib.foo_bar.viewplugin

import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.badoo.ribs.core.Plugin
import com.badoo.ribs.example.rib.foo_bar.FooBarView

class ParentLongClickListener : Plugin<FooBarView> {

    private val listener = View.OnLongClickListener {
        Log.d("ParentLongClickListener", "onLongClick")
        false
    }

    override fun onAttachToView(parentViewGroup: ViewGroup) {
        parentViewGroup.setOnLongClickListener(listener)
    }

    override fun onDetachFromView(parentViewGroup: ViewGroup) {
        parentViewGroup.setOnLongClickListener(null)
    }
}
