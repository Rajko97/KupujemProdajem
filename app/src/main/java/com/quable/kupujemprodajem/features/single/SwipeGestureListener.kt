package com.quable.kupujemprodajem.features.single

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

private const val X_DIF_THRESHOLD = 100
private const val X_VELOCITY_THRESHOLD = 100

class SwipeGestureListener(context: Context) : View.OnTouchListener {

    var onSwipeLeft: (() -> Unit)? = null
    var onSwipeRight: (() -> Unit)? = null

    private val gestureDetector: GestureDetector

    private val listener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float,
        ): Boolean {
            e1?.let {
                val xDiff = e2.x - e1.x
                val yDiff = e2.y - e1.y

                if (abs(xDiff) > abs(yDiff)) {
                    if (abs(xDiff) > X_DIF_THRESHOLD && abs(velocityX) > X_VELOCITY_THRESHOLD) {
                        if (xDiff > 0) {
                            onSwipeRight?.invoke()
                        } else {
                            onSwipeLeft?.invoke()
                        }
                    }
                }
            }
            return false
        }
    }

    init {
        gestureDetector = GestureDetector(context, listener)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let { return gestureDetector.onTouchEvent(it) } ?: return false
    }
}
