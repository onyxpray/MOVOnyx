package com.onyxpray.mov

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.onyxpray.mov.SignIn.SignInActivity
import kotlinx.android.synthetic.main.fragment_setting.*


class LogoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)


        rc_logout.setOnClickListener {
            fun OnClick(arg0: View?) {
                val myPrefs = getSharedPreferences(
                    "MY",
                    MODE_PRIVATE
                )
                val editor = myPrefs.edit()
                editor.clear()
                editor.commit()
                val intent = Intent(
                    this@LogoutActivity,
                    SignInActivity::class.java
                )
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }
    }
}