package com.devandfun.mytravel.base.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.devandfun.mytravel.base.R
import kotlin.math.max
import kotlin.math.min

class DraggableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defRes) {
    // android helper for view dragging
    private lateinit var dragHelper: ViewDragHelper
    // current state of drawer, open or not
    var isOpen = false
        private set(value) {
            field = value
            toggleListener?.invoke(value)
        }
    // state of dragging
    private var mCurrentDraggingState: Int = 0
    // max height of view in opened state
    private var verticalRange: Int = 0
    // top of view
    private var draggingBorder: Int = 0
    // view for dragging all panel
    private var draggableView: View? = null
    private var draggableViewId: Int = -1
    // for clicks handling
    private var lastTappedX: Float = 0.toFloat()
    private var lastTappedY: Float = 0.toFloat()
    private var lastTappedTimestamp: Long = 0

    private var expandedInitialState: Boolean? = false

    var toggleListener: ((Boolean) -> Unit)? = null

    companion object {
        // speed of swipe for force open/hide drawer
        private const val autoOpenSpeedLimit = 800f
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DraggableView)
            draggableViewId =
                typedArray.getResourceId(R.styleable.DraggableView_bdv_draggableView, -1)
            expandedInitialState =
                typedArray.getBoolean(R.styleable.DraggableView_bdv_expanded, false)
            typedArray.recycle()
        }
    }

    override fun onFinishInflate() {
        dragHelper = ViewDragHelper.create(this, 1.0f, DragHelperCallback())
        super.onFinishInflate()
        if (draggableViewId != -1)
            draggableView = findViewById(draggableViewId)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // place content of drawer to only draggable view visible state
        expandedInitialState?.let {
            isOpen = it
            expandedInitialState = null
        }
        if (!isOpen) {
            val content = getChildAt(0)
            if (draggableView != null) {
                content.offsetTopAndBottom(content.measuredHeight - draggableView!!.measuredHeight)
                draggingBorder = content.top
            } else {
                content.offsetTopAndBottom(content.measuredHeight)
            }
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        verticalRange = if (draggableView != null) {
            height - draggableView!!.measuredHeight
        } else height
        super.onSizeChanged(width, height, oldWidth, oldHeight)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // need check if we drag our draggable view
        if (isDraggableViewTarget(event) && dragHelper.shouldInterceptTouchEvent(event)) {
            return true
        }
        return false
    }

    @SuppressWarnings("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // if we already started dragging or stat dragging by this tap
        if (isMoving() || isDraggableViewTarget(event)) {
            dragHelper.processTouchEvent(event)
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastTappedX = event.x
                    lastTappedY = event.y
                    lastTappedTimestamp = System.currentTimeMillis()
                }
                MotionEvent.ACTION_UP -> {
                    val endX = event.x
                    val endY = event.y
                    if (isClickEvent(lastTappedX, endX, lastTappedY, endY, lastTappedTimestamp)) {
                        toggle() // WE HAVE A CLICK!!
                    }
                }
            }
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun computeScroll() {
        // resume auto open animation
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    /**
     * programmatically toggle state of drawer
     */
    fun toggle() {
        if (mCurrentDraggingState != ViewDragHelper.STATE_DRAGGING) {
            val destY = if (isOpen) {
                verticalRange
            } else 0
            dragHelper.smoothSlideViewTo(getChildAt(0), 0, destY)
            ViewCompat.postInvalidateOnAnimation(this)
        } else {
            dragHelper.abort()
        }
    }

    fun close() {
        if (mCurrentDraggingState != ViewDragHelper.STATE_DRAGGING && isOpen) {
            dragHelper.smoothSlideViewTo(getChildAt(0), 0, verticalRange)
            ViewCompat.postInvalidateOnAnimation(this)
        } else {
            dragHelper.abort()
        }
    }

    /**
     * detect if MotionEvent applied to our draggable view
     * @param event - motion event for BottomDrawerView
     * @return true if event in draggableView area
     */
    private fun isDraggableViewTarget(event: MotionEvent): Boolean {
        return if (draggableView != null) {
            val queenLocation = IntArray(2)
            draggableView!!.getLocationOnScreen(queenLocation)
            val upperLimit = queenLocation[1] + draggableView!!.measuredHeight
            val lowerLimit = queenLocation[1]
            val y = event.rawY.toInt()
            y in (lowerLimit + 1) until upperLimit
        } else true
    }

    private fun isMoving(): Boolean {
        return mCurrentDraggingState == ViewDragHelper.STATE_DRAGGING || mCurrentDraggingState == ViewDragHelper.STATE_SETTLING
    }

    private fun isClickEvent(
        startX: Float,
        endX: Float,
        startY: Float,
        endY: Float,
        lastTappedTimestamp: Long
    ): Boolean {
        val differenceX = Math.abs(startX - endX)
        val differenceY = Math.abs(startY - endY)
        val isClickByTime =
            ViewConfiguration.getTapTimeout() >= System.currentTimeMillis() - lastTappedTimestamp
        return !(differenceX > ViewConfiguration.get(context).scaledTouchSlop || differenceY > ViewConfiguration.get(
            context
        ).scaledTouchSlop) && isClickByTime
    }

    inner class DragHelperCallback : ViewDragHelper.Callback() {

        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {
            draggingBorder = top
            super.onViewPositionChanged(changedView, left, top, dx, dy)
        }

        override fun onViewDragStateChanged(state: Int) {
            if (mCurrentDraggingState == state) {
                return
            }
            if ((mCurrentDraggingState == ViewDragHelper.STATE_DRAGGING || mCurrentDraggingState == ViewDragHelper.STATE_SETTLING) &&
                state == ViewDragHelper.STATE_IDLE
            ) {
                // the view stopped from moving.
                if (draggingBorder == verticalRange) {
                    isOpen = false
                }
                if (draggingBorder == 0) {
                    isOpen = true
                }
            }
            mCurrentDraggingState = state
        }

        override fun tryCaptureView(view: View, pointerId: Int): Boolean {
            // specify view for dragging
            return view.id == getChildAt(0).id
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return min(max(top, paddingTop), verticalRange)
        }

        override fun onViewReleased(releasedChild: View, xVelocity: Float, yVelocity: Float) {
            super.onViewReleased(releasedChild, xVelocity, yVelocity)
            // auto open or hide when needed here
            val rangeToCheck = verticalRange
            if (draggingBorder == 0 || draggingBorder == rangeToCheck) {
                return
            }

            val settleToOpen = getSettleToOpen(rangeToCheck, yVelocity)
            animateState(settleToOpen)
        }

        private fun getSettleToOpen(rangeToCheck: Int, yVelocity: Float): Boolean =
            when {
                yVelocity > autoOpenSpeedLimit -> true
                yVelocity < -autoOpenSpeedLimit -> false
                draggingBorder > rangeToCheck / 2 -> true
                draggingBorder < rangeToCheck / 2 -> false
                else -> false
            }

        // can be triggered only from onViewReleased
        private fun animateState(settleToOpen: Boolean) {
            val settleDestY = if (settleToOpen) {
                verticalRange
            } else 0

            if (dragHelper.settleCapturedViewAt(0, settleDestY)) {
                ViewCompat.postInvalidateOnAnimation(this@DraggableView)
            }
        }
    }
}