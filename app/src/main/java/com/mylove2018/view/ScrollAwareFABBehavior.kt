package com.mylove2018.view

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.View

/**
 * Created by 11833 on 2018/1/4.
 */

class ScrollAwareFABBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<FloatingActionButton>() {
    private var mIsAnimatingOut = false
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        //处理垂直方向上的滚动事件
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        //向上滚动进入，向下滚动隐藏
        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.visibility == View.VISIBLE) {
            //animateOut()和animateIn()都是私有方法，需要重新实现
            animateOut(child)
        } else if (dyConsumed < 0 && child.visibility != View.VISIBLE) {
            animateIn(child)
        }
    }

    private fun animateOut(button: FloatingActionButton) {
        ViewCompat.animate(button).translationY(500f)
                .setInterpolator(INTERPOLATOR).withLayer()
                .setListener(object : ViewPropertyAnimatorListener {
                    override fun onAnimationStart(view: View) {
                        this@ScrollAwareFABBehavior.mIsAnimatingOut = true
                    }

                    override fun onAnimationEnd(view: View) {
                        this@ScrollAwareFABBehavior.mIsAnimatingOut = false
                        view.visibility = View.GONE
                    }

                    override fun onAnimationCancel(view: View) {
                        this@ScrollAwareFABBehavior.mIsAnimatingOut = false

                    }
                }).start()
    }

    private fun animateIn(button: FloatingActionButton) {
        button.visibility = View.VISIBLE
        ViewCompat.animate(button).translationY(0f)
                .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                .start()
    }

    companion object {

        private val INTERPOLATOR = FastOutSlowInInterpolator()
    }
}