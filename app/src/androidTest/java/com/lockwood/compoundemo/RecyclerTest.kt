package com.lockwood.compoundemo

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecyclerTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun setDrawables_sameActivity() {
        onView(withId(R.id.pager)).perform(swipeLeft())

        Thread.sleep(THREE_SECONDS)
        scrollToPosition(POSITION_PICASSO)
        Thread.sleep(THREE_SECONDS)
        scrollToPosition(POSITION_COIL)
        Thread.sleep(THREE_SECONDS)
    }

    private fun scrollToPosition(position: Int) = onView(withId(R.id.recyclerView)).perform(
        RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position)
    )

    companion object {

        private const val THREE_SECONDS = 3000L

        private const val POSITION_PICASSO = 199
        private const val POSITION_COIL = 299
    }
}