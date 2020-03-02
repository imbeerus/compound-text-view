package com.lockwood.compound

import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View

/**
 * Interface used to handle and delegate clicks on TextView and his drawables
 */
interface CompoundViewClickListener {

    /**
     * This method will be invoked when view is clicked (none of the drawables was clicked)
     *
     * @param view  that received the click
     * @param event containing full information about the event
     */
    fun onViewClick(view: View, event: MotionEvent)

    /**
     * This method will be invoked when drawable at start is clicked
     *
     * @param view that received the click
     * @param event containing full information about the event
     */
    fun onStartDrawableClick(view: View, drawable: Drawable, event: MotionEvent)

    /**
     * This method will be invoked when drawable at top is clicked
     *
     * @param view that received the click
     * @param event containing full information about the event
     */
    fun onTopDrawableClick(view: View, drawable: Drawable, event: MotionEvent)

    /**
     * This method will be invoked when drawable at end is clicked
     *
     * @param view that received the click
     * @param event containing full information about the event
     */
    fun onEndDrawableClick(view: View, drawable: Drawable, event: MotionEvent)

    /**
     * This method will be invoked when drawable at bottom is clicked
     *
     * @param view that received the click
     * @param event containing full information about the event
     */
    fun onBottomDrawableClick(view: View, drawable: Drawable, event: MotionEvent)

}