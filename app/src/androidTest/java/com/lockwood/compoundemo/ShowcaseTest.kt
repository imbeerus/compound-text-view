package com.lockwood.compoundemo

import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lockwood.compound.CompoundTextView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.hamcrest.core.StringContains.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ShowcaseTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    private val drawablesPositions = arrayOf(R.id.top, R.id.end, R.id.bottom)

    @Test
    fun setDrawables_sameActivity() {
        setAllDrawablesChecked()

        onView(withId(R.id.compound)).check(matches(matchDrawablesCount()))
    }

    @Test
    fun setDrawablesGravityCenter_sameActivity() {
        setAllDrawablesChecked()

        val spinnerId = R.id.gravity
        val selectionText = "Center"
        onView(withId(spinnerId)).perform(click())
        selectSpinnerText(selectionText)

        checkSpinnerText(spinnerId, selectionText)
    }

    @Test
    fun setAttachedToTextAll_sameActivity() {
        setAllDrawablesChecked()

        val spinnerId = R.id.attachedToText
        val selectionText = "All"
        onView(withId(spinnerId)).perform(click())
        selectSpinnerText(selectionText)

        checkSpinnerText(spinnerId, selectionText)
    }

    @Test
    fun setDrawablePadding_sameActivity() {
        setAllDrawablesChecked()

        val spinnerId = R.id.padding
        val selectionText = "32"
        onView(withId(spinnerId)).perform(click())
        selectSpinnerText(selectionText)

        checkSpinnerText(spinnerId, selectionText)
    }

    private fun matchDrawablesCount(): Matcher<View?>? =
        object : BoundedMatcher<View?, CompoundTextView>(CompoundTextView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("Checking the matcher on received view: with $DRAWABLES_TO_SHOW compound drawables")
            }

            override fun matchesSafely(foundView: CompoundTextView) =
                foundView.drawables.size == DRAWABLES_TO_SHOW

        }

    private fun selectSpinnerText(text: String) =
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(text))).perform(click())

    private fun checkSpinnerText(id: Int, text: String) =
        onView(withId(id)).check(matches(withSpinnerText(containsString(text))))

    private fun setAllDrawablesChecked() = drawablesPositions.forEach { id ->
        onView(withId(id)).check(matches(ViewMatchers.isNotChecked())).perform(click());
    }

    companion object {

        private const val DRAWABLES_TO_SHOW = 4
    }

}