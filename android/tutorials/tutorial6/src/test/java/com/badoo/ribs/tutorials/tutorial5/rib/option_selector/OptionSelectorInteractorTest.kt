package com.badoo.ribs.tutorials.tutorial5.rib.more_options

import com.badoo.ribs.tutorials.tutorial5.rib.more_options.feature.MoreOptionsFeature
import com.badoo.ribs.tutorials.tutorial5.rib.option_selector.OptionSelector
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import org.junit.After
import org.junit.Before
import org.junit.Test

class OptionSelectorInteractorTest {

    private val input: ObservableSource<OptionSelector.Input> = mock()
    private val output: Consumer<OptionSelector.Output> = mock()
    private val feature: MoreOptionsFeature = mock()
    private val router: OptionSelectorRouter = mock()
    private lateinit var interactor: OptionSelectorInteractor

    @Before
    fun setup() {
        interactor = OptionSelectorInteractor(
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
