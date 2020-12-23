package com.onyxpray.mov.Checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.onyxpray.mov.Models.Film
import com.onyxpray.mov.Checkout.Checkout
import com.onyxpray.mov.R
import kotlinx.android.synthetic.main.activity_pilih_bangku.*

class PilihBangkuActivity : AppCompatActivity() {
    var statusA3: Boolean = false
    var statusA4: Boolean = false
    var total: Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)
        val data = intent.getParcelableExtra<Film>("data")

        tv_kursi.text = data!!.judul

        a3.setOnClickListener {
            if (statusA3) {
                a3.setImageResource(R.drawable.ic_baseline_weekend_grey)
                statusA3 = false
                total -= 1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("A3", "70000"))

            } else {
                a3.setImageResource(R.drawable.ic_baseline_weekend_lightblue)
                statusA3 = true
                total += 1
                belitiket(total)

                val data = Checkout("A3", "65000")
                dataList.add(data)
            }
        }

        a4.setOnClickListener {
            if (statusA4) {
                a4.setImageResource(R.drawable.ic_baseline_weekend_grey)
                statusA4 = false
                total -= 1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("A4", "65000"))

            } else {
                a4.setImageResource(R.drawable.ic_baseline_weekend_lightblue)
                statusA4 = true
                total += 1
                belitiket(total)

                val data = Checkout("A4", "65000")
                dataList.add(data)
            }
        }

        btn_home.setOnClickListener {

            val intent = Intent(
                    this,
                    CheckoutActivity::class.java
            ).putExtra("data", dataList).putExtra("datas", data)
            startActivity(intent)
        }
    }

    private fun belitiket(total: Int) {
        if (total == 0) {
            btn_home.setText("Beli Tiket")
            btn_home.visibility = View.INVISIBLE
        } else {
            btn_home.setText("Beli Tiket (" + total + ")")
            btn_home.visibility = View.VISIBLE
        }
    }
}
