package com.onyxpray.mov.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onyxpray.mov.R
import com.onyxpray.mov.SignIn.SignInActivity
import com.onyxpray.mov.Utils.Preferences
import kotlinx.android.synthetic.main.activity_on_boarding1.*


class OnBoarding1Activity : AppCompatActivity() {
    lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding1)

        preferences = Preferences(this)

        if (preferences.getValues("onboarding").equals("1")) {
            finishAffinity()

            val intent = Intent(this@OnBoarding1Activity,
                SignInActivity::class.java)
            startActivity(intent)
        }
        btn_home.setOnClickListener {
            val intent = Intent(this@OnBoarding1Activity,
                    OnBoarding2Activity::class.java)
            startActivity(intent)
        }

        btn_daftar.setOnClickListener {
            preferences.setValues("onboarding", "1")
            finishAffinity()

            val intent = Intent(this@OnBoarding1Activity,
                    SignInActivity::class.java)
            startActivity(intent)
        }
    }

}