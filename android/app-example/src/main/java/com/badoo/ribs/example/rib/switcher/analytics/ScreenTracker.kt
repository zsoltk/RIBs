package com.badoo.ribs.example.rib.switcher.analytics

import android.util.Log
import com.badoo.ribs.core.Node
import com.badoo.ribs.example.util.CanReportViewScreen
import com.badoo.ribs.util.FindNodeTask
import com.badoo.ribs.util.findNodes

class ScreenTracker {

    var currentScreenName: String? = null
        set(value) {
            field = value
            Log.d("FindNodeTask", "Set screen name to: $value")
        }

    fun trackViewScreen() {
        Log.d("FindNodeTask", "ViewScreenEvent: $currentScreenName")
    }

    fun refresh(node: Node<*>) {
        Log.d("FindNodeTask", "refresh")
        node.findNodes(
            FindNodeTask(
                name ="CanReportViewScreen",
                predicate = { it is CanReportViewScreen },
                childSelector = { it.childrenAttachedToView },
                policy = FindNodeTask.Policy.OneOrZeroAllowed
            )
        ).firstOrNull().let {
            val screenName = (it as? CanReportViewScreen)?.screenName
            if (currentScreenName != screenName) {
                currentScreenName = screenName
                trackViewScreen()
            }
        }
    }
}
