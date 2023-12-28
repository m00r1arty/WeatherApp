package com.example.weatherapp.ui

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.GrantPermissionRule
import com.example.weatherapp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(
            "android.permission.ACCESS_FINE_LOCATION"
        )

    @Test
    fun searchButtonTest() {
        val appCompatImageButton = onView(
            allOf(
                withId(R.id.searchButton), withContentDescription("TODO"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cardView),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val editText = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(android.R.id.custom),
                        childAtPosition(
                            withClassName(Matchers.`is`("android.widget.FrameLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText.perform(replaceText("Dushanbe"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(android.R.id.button1), withText("Ok"),
                childAtPosition(
                    childAtPosition(
                        withClassName(Matchers.`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton.perform(scrollTo(), click())
    }


    @Test
    fun syncButtonTest() {
        val appCompatImageButton2 = onView(
            allOf(
                withId(R.id.syncButton), withContentDescription("TODO"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cardView),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())
    }

    @Test
    fun celsiusToFahrenheitButton() {
        val celsiusToFahrenheitButton = onView(
            allOf(
                withId(R.id.celsiusToFahrenheitButton),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cardView2),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        ).perform(waitFor())

        celsiusToFahrenheitButton.perform(click())
    }

    private fun waitFor(): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Wait for 4000 milliseconds."
            }

            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(View::class.java)
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(7000)
            }
        }
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
