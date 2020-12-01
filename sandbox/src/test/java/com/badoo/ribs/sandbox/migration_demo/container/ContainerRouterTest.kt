package com.badoo.ribs.sandbox.migration_demo.container

import com.badoo.ribs.core.builder.BuildParams
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ContainerRouterTest {

    private var interactor: ContainerInteractor = mock()
    private var router: ContainerRouter? = null

    @Before
    fun setup() {
        router = ContainerRouter(
            buildParams = BuildParams.Empty()
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
