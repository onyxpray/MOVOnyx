package com.onyxpray.mov.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onyxpray.mov.R
import com.onyxpray.mov.SignIn.SignInActivity
import kotlinx.android.synthetic.main.activity_on_boarding3.*

class OnBoarding3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding3)

        btn_home.setOnClickListener {
            finishAffinity()

            val intent = Intent(
                this@OnBoarding3Activity,
                SignInActivity::class.java
            )
            startActivity(intent)
        }
    }
}