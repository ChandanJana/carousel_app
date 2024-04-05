package com.carouselapplication

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

/**
 * Created by Chandan Jana on 04-04-2024.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
class CarouselView : FrameLayout {

    private lateinit var gestureDetector: GestureDetector
    private var currentIndex = 0
    private var itemClickListener: ((Int) -> Unit)? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        gestureDetector =  GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (e1 != null && e2 != null) {
                    if (e1.x - e2.x > SWIPE_THRESHOLD && currentIndex < childCount - 1) {
                        showNextItem()
                        return true
                    } else if (e2.x - e1.x > SWIPE_THRESHOLD && currentIndex > 0) {
                        showPreviousItem()
                        return true
                    }
                }
                return false
            }

            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                itemClickListener?.invoke(currentIndex)
                return true
            }

        })
    }

    private fun showNextItem() {
        getChildAt(currentIndex).visibility = View.GONE
        currentIndex++
        getChildAt(currentIndex).visibility = View.VISIBLE
    }

    private fun showPreviousItem() {
        getChildAt(currentIndex).visibility = View.GONE
        currentIndex--
        getChildAt(currentIndex).visibility = View.VISIBLE
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    companion object {
        private const val SWIPE_THRESHOLD = 100
    }
}
