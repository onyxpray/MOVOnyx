package com.onyxpray.mov.Checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.onyxpray.mov.Models.Film
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlin.collections.ArrayList
import com.onyxpray.mov.R
import com.onyxpray.mov.Utils.Preferences
import java.text.NumberFormat
import java.util.*


class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total:Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>
        val data = intent.getParcelableExtra<Film>("datas")

        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total Harus Dibayar", total.toString()))

        rc_checkout.layoutManager = LinearLayoutManager(this)
        rc_checkout.adapter = CheckoutAdapter(dataList) {
        }

        btn_tiket.setOnClickListener {
            var intent = Intent(this,
                CheckoutSuccessActivity::class.java)
            startActivity(intent)


        }

        btn_home.setOnClickListener {
            finish()
        }

        if(preferences.getValues("saldo")!!.isNotEmpty()) {
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            tv_saldo.setText(formatRupiah.format(preferences.getValues("saldo")!!.toDouble()))
            btn_tiket.visibility = View.VISIBLE
            textView3.visibility = View.INVISIBLE

        } else {
            tv_saldo.setText("Rp 0")
            btn_tiket.visibility = View.INVISIBLE
            textView3.visibility = View.VISIBLE
            textView3.text = "Saldo pada e-wallet kamu tidak mencukupi\n" +
                    "untuk melakukan transaksi"
        }
    }}




