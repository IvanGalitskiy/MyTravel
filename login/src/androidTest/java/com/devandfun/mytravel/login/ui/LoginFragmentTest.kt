package com.devandfun.mytravel.login.ui

import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.devandfun.mytravel.base.entities.Profile
import com.devandfun.mytravel.base.tests.TestActivity
import com.devandfun.mytravel.login.LoginFeature
import com.devandfun.mytravel.login.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {
    @get:Rule
    var activityRule: ActivityTestRule<TestActivity> = ActivityTestRule(TestActivity::class.java)

    @Test
    fun test() {
        val activity = activityRule.activity as TestActivity
        LoginFeature.LoginStarter(fragmentManager = activity.supportFragmentManager,
            container = activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup,
            dependencies = object : LoginFeature.Dependencies {
                override fun callback(): LoginFeature.Callback {
                    return object : LoginFeature.Callback {
                        override fun onLogin(selectedProfile: Profile) { }
                    }
                }
            }).start()
        onView(withId(R.id.vSelectProfileTitle)).check(matches(withText("SELECT YOUR PROFILE")))
    }
}