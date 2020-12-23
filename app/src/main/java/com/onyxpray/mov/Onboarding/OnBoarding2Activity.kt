package com.onyxpray.mov.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onyxpray.mov.R
import kotlinx.android.synthetic.main.activity_on_boarding2.*

class OnBoarding2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding2)

        btn_home.setOnClickListener {
            val intent = Intent(this@OnBoarding2Activity,
                    OnBoarding3Activity::class.java)
            startActivity(intent)
        }
    }
}