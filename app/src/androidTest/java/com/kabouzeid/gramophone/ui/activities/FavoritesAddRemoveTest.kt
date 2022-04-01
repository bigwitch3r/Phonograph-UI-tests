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

/*
* FavoritesAddRemoveTest - Тест, осуществляющий добавление и удаление трека из Избранного
* Осуществляет выбор первого трека в списке, раскрытие полноразмерного проигрывателя,
* а также два нажатия по кнопке добавления трека в избранное
 */

@LargeTest
@RunWith(AndroidJUnit4::class)
class FavoritesAddRemoveTest {

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
    fun favoritesAddRemoveTest() {
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

        // Выбираем первый трек в списке
        val frameLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recycler_view),
                                childAtPosition(
                                        withId(R.id.container),
                                        0)),
                        1),
                        isDisplayed()))
        frameLayout.perform(click())

        // Нажимаем на мини-проигрыватель внизу экрана
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

        // Дважды нажимаем на кнопку в форме сердечка для добавления/удаления из Избранного
        val actionMenuItemView = onView(
                allOf(withId(R.id.action_toggle_favorite),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.player_toolbar),
                                        0),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        val actionMenuItemView2 = onView(
                allOf(withId(R.id.action_toggle_favorite),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.player_toolbar),
                                        0),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())
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
