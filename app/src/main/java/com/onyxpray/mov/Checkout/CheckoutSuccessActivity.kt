package com.onyxpray.mov.Checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onyxpray.mov.Home.HomeScreenActivity
import com.onyxpray.mov.MyWalletActivity
import com.onyxpray.mov.R
import com.onyxpray.mov.TiketActivity
import kotlinx.android.synthetic.main.activity_checkout_success.*
import kotlinx.android.synthetic.main.fragment_setting.*

class CheckoutSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)
        btn_tiket.setOnClickListener {

            val activity = this@CheckoutSuccessActivity
            startActivity(Intent(activity, TiketActivity::class.java))}

            btn_home.setOnClickListener {
                finishAffinity()

                val intent = Intent(
                    this@CheckoutSuccessActivity,
                    HomeScreenActivity::class.java
                )
                startActivity(intent)
            }


        }
    }
