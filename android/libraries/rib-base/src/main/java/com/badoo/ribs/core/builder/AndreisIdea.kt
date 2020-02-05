//package com.badoo.ribs.core.builder
//
//import androidx.lifecycle.Lifecycle
//import com.badoo.ribs.core.Builder
//import com.badoo.ribs.core.Node
//import com.badoo.ribs.core.Interactor
//import com.badoo.ribs.core.view.RibView
//
//
//class FakeBuilder: Builder<Unit>() {
//    override val dependency: Unit
//        get() = Unit
//
//    fun build() =
////        Node(
////            interactor = createInteractor<RibView> {
////                onViewCreated { view, lifecycle ->
////
////                }
////            }
////        )
//
//        Node<RibView>(
//            interactor = interactor {
//                onViewCreated { view, lifecycle ->
//
//                }
//            }
//        )
//}
//
//fun <V : RibView> Builder<*>.interactor(builder: InteractorBuilder<V>.() -> Unit): Interactor<V> =
//    InteractorBuilder<V>().apply(builder).build()
//
//class InteractorBuilder<V: RibView> {
//    internal var onViewCreated: (V, Lifecycle) -> Unit = {  }
//
//    fun onViewCreated(block: (V, Lifecycle) -> Unit) {
//        onViewCreated = block
//    }
//}
//
//private fun <V : RibView> InteractorBuilder<V>.build() =
//    object : Interactor<V>() {
//        override fun onViewCreated(view: V, viewLifecycle: Lifecycle) {
//            onViewCreated.invoke(view, viewLifecycle)
//        }
//    }
//}
