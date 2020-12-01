package com.badoo.ribs.sandbox.migration_demo.leaf

import com.badoo.ribs.core.builder.BuildContext
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import org.junit.After
import org.junit.Before
import org.junit.Test

class LeafWorkflowTest {

    private lateinit var workflow: Leaf

    @Before
    fun setup() {
        workflow = LeafBuilder(object : Leaf.Dependency {
            override fun leafInput(): ObservableSource<Leaf.Input> = mock()
            override fun leafOutput(): Consumer<Leaf.Output> = mock()
        }).build(BuildContext.root(savedInstanceState = null)).also {
            it.node.onAttach()
        }
    }

    @After
    fun tearDown() {
    }

    /**
     * TODO: Add tests for every workflow action that operates on the node
     */
    @Test
    fun `business logic operation test`() {
        workflow.businessLogicOperation()
        // verify(feature).accept(Wish)

        throw RuntimeException("Add real tests.")
    }
}
