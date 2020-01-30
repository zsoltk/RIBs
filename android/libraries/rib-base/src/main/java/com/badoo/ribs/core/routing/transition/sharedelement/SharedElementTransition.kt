package com.badoo.ribs.core.routing.transition.sharedelement

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.annotation.CheckResult
import com.badoo.ribs.core.routing.transition.SingleProgressEvaluator
import com.badoo.ribs.core.routing.transition.TransitionDirection
import com.badoo.ribs.core.routing.transition.TransitionElement
import com.badoo.ribs.core.routing.transition.Transition
import com.badoo.ribs.core.routing.transition.handler.defaultDuration
import com.badoo.ribs.core.routing.transition.handler.defaultInterpolator
import com.badoo.ribs.core.routing.transition.sharedelement.SharedElementTransition.RotationParams

interface SharedElementTransition {
    data class Params(
        val duration: Long = defaultDuration,
        val exitingElementMatcher: (View) -> View?,
        val enteringElementMatcher: (View) -> View?,
        val translateXInterpolator: Interpolator = LinearInterpolator(),
        val translateYInterpolator: Interpolator = LinearInterpolator(),
        val scaleXInterpolator: Interpolator = LinearInterpolator(),
        val scaleYInterpolator: Interpolator = LinearInterpolator(),
        val rotation: RotationParams? = null,
        val rotationX: RotationParams? = null,
        val rotationY: RotationParams? = null
    )

    data class RotationParams(
        val degrees: Float,
        val interpolator: Interpolator = defaultInterpolator
    )
}

internal data class SharedElementTransitionInfo<T>(
    val exitingElement: TransitionElement<out T>,
    val exitingView: View,
    val enteringElement: TransitionElement<out T>,
    val enteringView: View,
    val abandoned: View?,
    val params: SharedElementTransition.Params
)

@CheckResult
fun <T> List<TransitionElement<out T>>.sharedElementTransition(
    transitionParams: List<SharedElementTransition.Params>
): Transition {
    val exit = filter { it.direction == TransitionDirection.Exit }
    val enter = filter { it.direction == TransitionDirection.Enter }
    val transitions: MutableList<SharedElementTransitionInfo<T>> = mutableListOf()

    transitionParams.forEach { transitionParam ->
        var exitingView: View? = null
        var enteringView: View? = null

        val exitingElement = exit.find {
            exitingView = transitionParam.exitingElementMatcher.invoke(it.view)
            exitingView != null
        }

        val enteringElement = enter.find {
            enteringView = transitionParam.enteringElementMatcher.invoke(it.view)
            enteringView != null
        }

        var previous: View? = null
        if (enteringElement != null && exitingElement != null && exitingView!!.visibility == View.INVISIBLE) {
            val rootView = enteringElement.view.rootView as ViewGroup
            // Only look for direct children, no need to findViewById and traverse the whole screen
            loop@ for (i in 0 until rootView.childCount) {
                val currentChild = rootView.getChildAt(i)
                if (currentChild.id == enteringView!!.id) {
                    exitingView!!.visibility = View.VISIBLE
                    previous = currentChild
                    break@loop
                }
            }
        }

        if (exitingElement != null && enteringElement != null) {
            transitions.add(
                SharedElementTransitionInfo(
                    exitingElement = exitingElement,
                    exitingView = exitingView!!, // guaranteed by find clause
                    enteringElement = enteringElement,
                    enteringView = enteringView!!, // guaranteed by find clause
                    abandoned = previous,
                    params = transitionParam
                )
            )
        }
    }

    return Transition.multiple(
        transitions.map {
            it.transition()
        }
    )
}

@CheckResult
internal fun <T> SharedElementTransitionInfo<T>.transition(): Transition {
    val evaluator = SingleProgressEvaluator()
    exitingElement.progressEvaluator.add(evaluator)
    enteringElement.progressEvaluator.add(evaluator)

    enteringElement.view.measure(0, 0)

    val location = IntArray(2)
    enteringView.getLocationInWindow(location)
    val enteringAbsX = location[0]
    val enteringAbsY = location[1]

    abandoned?.let {
        exitingView.rotation = it.rotation % 360
        exitingView.rotationX = it.rotationX % 360
        exitingView.rotationY = it.rotationY % 360
        exitingView.scaleX = it.scaleX * it.width / exitingView.width
        exitingView.scaleY = it.scaleY * it.height / exitingView.height
    }

    // TODO should be scaleXY delta only, not full value in case it already had some, just increased
    val unaccountedWDiff = (exitingView.scaleX - 1) * exitingView.width
    val unaccountedHDiff = (exitingView.scaleY - 1) * exitingView.height

    (abandoned ?: exitingView).getLocationInWindow(location)
    val exitingAbsX = location[0]
    val exitingAbsY = location[1]

    val initialRotation = exitingView.rotation
    val initialRotationX = exitingView.rotationX
    val initialRotationY = exitingView.rotationY
    val initialScaleX = exitingView.scaleX
    val initialScaleY = exitingView.scaleY

    val targetScaleX = enteringView.scaleX * enteringView.measuredWidth / exitingView.width
    val targetScaleY = enteringView.scaleY * enteringView.measuredHeight / exitingView.height
//    val wDiff = (enteringView.measuredWidth - exitingView.scaleX * exitingView.width)
//    val hDiff = (enteringView.measuredHeight - exitingView.scaleY * exitingView.height)
    val wDiff = enteringView.measuredWidth - exitingView.measuredWidth
    val hDiff = enteringView.measuredHeight - exitingView.measuredHeight
//    val wDiff = enteringView.measuredWidth - (exitingView.width + unaccountedWDiff / 2)
//    val hDiff = enteringView.measuredHeight - (exitingView.height + unaccountedHDiff / 2)
//    val wDiff = enteringView.measuredWidth - exitingView.width * exitingView.scaleX
//    val hDiff = enteringView.measuredHeight - exitingView.height * exitingView.scaleY
//    val wDiff = enteringView.measuredWidth - exitingView.width * exitingView.scaleX
//    val hDiff = enteringView.measuredHeight - exitingView.height * exitingView.scaleY

//    val initialPosX = exitingAbsX - unaccountedWDiff / 2
//    val initialPosY = exitingAbsY - unaccountedHDiff / 2

    val targetXDiff = enteringAbsX - exitingAbsX + wDiff / 2f
    val targetYDiff = enteringAbsY - exitingAbsY + hDiff / 2f

    fun Float.x(): Float = params.translateXInterpolator.getInterpolation(this)
    fun Float.y(): Float = params.translateYInterpolator.getInterpolation(this)
    fun Float.scaleX(): Float = params.scaleXInterpolator.getInterpolation(this)
    fun Float.scaleY(): Float = params.scaleYInterpolator.getInterpolation(this)
    // TODO consider initialRotationX & Y too:
    fun RotationParams.rotation(progress: Float): Float = (degrees - initialRotation) * interpolator.getInterpolation(progress)

    val valueAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = params.duration

        addListener(object : AnimatorListenerAdapter() {
            val originalParent = exitingView.parent as ViewGroup
            val originalParentIdx = originalParent.indexOfChild(exitingView)
            val originalLayoutParams = exitingView.layoutParams
            val originalTX = exitingView.translationX
            val originalTY = exitingView.translationY
            val rootView = exitingView.rootView as ViewGroup

            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                evaluator.start()
                originalParent.removeView(exitingView)
                rootView.addView(exitingView)
                enteringView.visibility = View.INVISIBLE
                abandoned?.let {
                    rootView.removeView(abandoned)
                }
            }

            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                super.onAnimationEnd(animation, isReverse)
                rootView.removeView(exitingView)
                if (isReverse) {
                    originalParent.addView(exitingView, originalParentIdx, originalLayoutParams)
                    exitingView.translationX = originalTX
                    exitingView.translationY = originalTY
                    evaluator.reset()
                } else {
                    enteringView.visibility = View.VISIBLE
                    evaluator.markFinished()
                }
            }
        })

        addUpdateListener { animation ->
            val progress = animation.animatedValue as Float
            exitingView.translationX = exitingAbsX + unaccountedWDiff / 2 + progress.x() * (targetXDiff - unaccountedWDiff / 2)  //- exitingLayoutParams.leftMargin
            exitingView.translationY = exitingAbsY + unaccountedHDiff / 2 + progress.y() * (targetYDiff - unaccountedHDiff / 2) //- exitingLayoutParams.topMargin
            exitingView.scaleX = initialScaleX + progress.scaleX() * (targetScaleX - initialScaleX)
            exitingView.scaleY = initialScaleY + progress.scaleY() * (targetScaleY - initialScaleY)
            params.rotation?.let { exitingView.rotation = initialRotation + it.rotation(progress) }
            params.rotationX?.let { exitingView.rotationX = initialRotationX + it.rotation(progress) }
            params.rotationY?.let { exitingView.rotationY = initialRotationY + it.rotation(progress) }
        }
    }

    return Transition.from(valueAnimator)
}
