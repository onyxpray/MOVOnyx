package com.onyxpray.mov.Home.Setting


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.onyxpray.mov.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.onyxpray.mov.EditProfileActivity
import com.onyxpray.mov.LogoutActivity
import com.onyxpray.mov.MyWalletActivity
import com.onyxpray.mov.Utils.Preferences
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 *
 *
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {

    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(context!!.applicationContext)

        iv_nama.text = preferences.getValues("nama")
        tv_email.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)
        tv_my_wallet.setOnClickListener {
            startActivity(Intent(activity, MyWalletActivity::class.java))
        }
        tv_edit_profile.setOnClickListener {
            startActivity(Intent(activity, EditProfileActivity::class.java))
        }
        rc_logout.setOnClickListener {
            startActivity(Intent(activity, LogoutActivity::class.java))
        }
    }}


