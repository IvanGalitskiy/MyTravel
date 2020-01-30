package com.devandfun.mytravel

import com.devandfun.mytravel.base.entities.Profile
import com.devandfun.mytravel.login.LoginFeature

object Features {
    class LoginDependencies: LoginFeature.Dependencies{
        override fun callback(): LoginFeature.Callback {
            return object: LoginFeature.Callback{
                override fun onLogin(selectedProfile: Profile) {

                }
            }
        }
    }
}