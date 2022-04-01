package com.kabouzeid.gramophone.ui.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TrackSwitchTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule: GrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE")

    @Test
    fun trackSwitchTest() {
        /*
        * При первом запуске приложение запускает приветственный экран с кнопкой Get started
        * Если при запуске теста этого экрана не будет, возникнет NoMatchingViewException
        * Чтобы избежать этой ситуации, мы отлавливаем данное исключение
        * Если оно возникло, мы ничего не делаем и переходим к следующему участку кода
         */
        try {
            val appCompatButton = onView(allOf(withText("Get started"), childAtPosition(allOf(withId(R.id.mi_button_cta), childAtPosition(withId(R.id.mi_frame), 4)), 0), isDisplayed()))
            appCompatButton.perform(click())
        } catch (e: NoMatchingViewException) {
        }

        // Нажимаем на первый трек в списке
        val frameLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recycler_view),
                                childAtPosition(
                                        withId(R.id.container),
                                        0)),
                        1),
                        isDisplayed()))
        frameLayout.perform(click())

        // Нажимаем на мини-проигрыватель внизу экрана, чтобы полностью его раскрыть
        val frameLayout2 = onView(
                allOf(withId(R.id.mini_player_fragment),
                        childAtPosition(
                                allOf(withId(R.id.sliding_panel),
                                        childAtPosition(
                                                withId(R.id.sliding_layout),
                                                1)),
                                1),
                        isDisplayed()))
        frameLayout2.perform(click())

        // Нажимаем три раза на кнопку "Следующий трек"
        val appCompatImageButton = onView(
                allOf(withId(R.id.player_next_button),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                1),
                        isDisplayed()))
        appCompatImageButton.perform(click())

        val appCompatImageButton2 = onView(
                allOf(withId(R.id.player_next_button),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                1),
                        isDisplayed()))
        appCompatImageButton2.perform(click())

        val appCompatImageButton3 = onView(
                allOf(withId(R.id.player_next_button),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                1),
                        isDisplayed()))
        appCompatImageButton3.perform(click())

        // Нажимаем еще три раза, но уже на кнопку "Предыдущий трек"
        val appCompatImageButton4 = onView(
                allOf(withId(R.id.player_prev_button),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                0),
                        isDisplayed()))
        appCompatImageButton4.perform(click())

        val appCompatImageButton5 = onView(
                allOf(withId(R.id.player_prev_button),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                0),
                        isDisplayed()))
        appCompatImageButton5.perform(click())

        val appCompatImageButton6 = onView(
                allOf(withId(R.id.player_prev_button),
                        childAtPosition(
                                allOf(withId(R.id.player_media_controller_container),
                                        childAtPosition(
                                                withId(R.id.playback_controls_fragment),
                                                1)),
                                0),
                        isDisplayed()))
        appCompatImageButton6.perform(click())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

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
