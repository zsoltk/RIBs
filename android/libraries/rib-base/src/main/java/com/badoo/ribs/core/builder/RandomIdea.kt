//package com.badoo.ribs.core.builder
//
//import android.os.Bundle
//import android.view.ViewGroup
//import android.widget.FrameLayout
//import com.badoo.ribs.core.other.Node
//
//
//private typealias Builder = (savedInstanceState: Bundle?) -> Node
//
//
//private fun usage1(): Builder = {
//    com.badoo.ribs.core.other.Node(
//        viewFactory = ViewFactory(
//            create = { parentView, viewScope ->
//                val view = FrameLayout(parentView.context)
//                viewScope.onDestroy {
//
//                }
//
//                view
//            }
//        )
//    )
//}
//
//private fun usage2(): Builder = {
//    com.badoo.ribs.core.other.Node(
//        viewFactory = ViewFactory(
//            create = { parentView, viewScope ->
//                val view = FrameLayout(parentView.context)
//                val myPresenter = MyPresenter(view)
//                view
//            }
//        )
//    )
//}
//
//private fun usage3(): Builder = {
//    com.badoo.ribs.core.other.Node(
//        viewFactory = ViewFactory(
//            create = { parentView, viewScope ->
//                val view = FrameLayout(parentView.context)
//
//                view
//            }
//        )
//    )
//}
//
//
//private class Node(
//    viewFactory: ViewFactory? = null
////    onBackPressed: () -> Boolean = { false }
//) {
////    fun viewFactory(block: ViewFactory.() -> Unit) {
////        // set to builder
////    }
//}
//
//private class ViewFactory(
//    create: (ViewGroup, ViewScope) -> ViewGroup
//)
//
//private class ViewScope {
//    // builder
//    fun onDestroy(block: ViewGroup.() -> Unit) {
//
//    }
//}
//
//private class MyPresenter(view: FrameLayout) {
//
//    fun onBackPressed(): Boolean {
//        return false
//    }
//}
