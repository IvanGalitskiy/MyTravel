package com.devandfun.mytravel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devandfun.mytravel.login.LoginFeature
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginStarter = LoginFeature.LoginStarter(
            supportFragmentManager,
            vMainLayout,
            Features.LoginDependecies()
        )
        loginStarter.start()
    }
}
