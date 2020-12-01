package com.badoo.ribs.sandbox.migration_demo.leaf

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.sandbox.migration_demo.leaf.feature.LeafFeature
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import org.junit.After
import org.junit.Before
import org.junit.Test

class LeafInteractorTest {

    private val input: ObservableSource<Leaf.Input> = mock()
    private val output: Consumer<Leaf.Output> = mock()
    private val feature: LeafFeature = mock()
    private lateinit var interactor: LeafInteractor

    @Before
    fun setup() {
        interactor = LeafInteractor(
            buildParams = BuildParams.Empty(),
            input = input,
            output = output,
            feature = feature
        )
    }

    @After
    fun tearDown() {
    }

    /**
     * TODO: Add real tests.
     */
    @Test
    fun `an example test with some conditions should pass`() {
        throw RuntimeException("Add real tests.")
    }
}
