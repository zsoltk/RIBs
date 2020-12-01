package com.badoo.ribs.sandbox.migration_demo.container

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.sandbox.migration_demo.container.feature.ContainerFeature
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import org.junit.After
import org.junit.Before
import org.junit.Test

class ContainerInteractorTest {

    private val input: ObservableSource<Container.Input> = mock()
    private val output: Consumer<Container.Output> = mock()
    private val feature: ContainerFeature = mock()
    private val router: ContainerRouter = mock()
    private lateinit var interactor: ContainerInteractor

    @Before
    fun setup() {
        interactor = ContainerInteractor(
            buildParams = BuildParams.Empty(),
            input = input,
            output = output,
            feature = feature,
            router = router
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
