package com.onyxpray.mov.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onyxpray.mov.R
import com.onyxpray.mov.Models.Plays
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PlaysAdapter(private var data: List<Plays>,
                   private val listener: (Plays) -> Unit)
    : RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    lateinit var ContextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ContextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_play, parent, false)

        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, ContextAdapter, position)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = view.findViewById(R.id.tv_kursi)

        private val tvImage: ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data: Plays, listener: (Plays) -> Unit, context : Context, position : Int) {

            tvTitle.text = data.nama

            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(tvImage)

            itemView.setOnClickListener {
                listener(data)
            }
        }

    }

}

