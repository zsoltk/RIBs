package com.badoo.ribs.tutorials.tutorial5.rib.more_options

import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class OptionSelectorRouterTest {

    private var interactor: OptionSelectorInteractor = mock()
    private var router: OptionSelectorRouter? = null

    @Before
    fun setup() {
        router = OptionSelectorRouter()
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
