package com.badoo.ribs.sandbox.migration_demo.container

import android.os.Bundle
import com.badoo.common.ribs.RibsRule
import com.badoo.ribs.RibTestActivity
import com.badoo.ribs.core.builder.BuildContext.Companion.root
import io.reactivex.Observable.empty
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import org.junit.Rule
import org.junit.Test

class ContainerTest {

    @get:Rule
    val ribsRule = RibsRule { activity, savedInstanceState -> buildRib(activity, savedInstanceState) }

    private fun buildRib(ribTestActivity: RibTestActivity, savedInstanceState: Bundle?) =
        ContainerBuilder(object : Container.Dependency {
            override fun containerInput(): ObservableSource<Container.Input> = empty()
            override fun containerOutput(): Consumer<Container.Output> = Consumer {}
        }).build(root(savedInstanceState))

    @Test
    fun testTextDisplayed() {
        TODO("Write UI tests")
    }
}
