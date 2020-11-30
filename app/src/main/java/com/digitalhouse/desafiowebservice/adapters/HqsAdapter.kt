package com.digitalhouse.desafiowebservice.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.digitalhouse.desafiowebservice.R
import com.digitalhouse.desafiowebservice.entities.Hqs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hqs.view.*

class HqsAdapter(private val listOfHqs: List<Hqs>, val listener: OnClickHqListener): RecyclerView.Adapter<HqsAdapter.HqsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HqsViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_hqs, parent, false)

        return HqsViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: HqsViewHolder, position: Int) {
        val current = listOfHqs[position]


        Glide.with(holder.itemView.context).asBitmap()
               .load(R.drawable.marvel_logo)
               .into(holder.image_hq)

        holder.number_hq.text = "#" + current.id.toString()

    }

    override fun getItemCount() = listOfHqs.size

    inner class HqsViewHolder(itemview: View): RecyclerView.ViewHolder(itemview), View.OnClickListener{
        val image_hq = itemview.item_image_hqs
        val number_hq = itemview.item_number_hqs

        init {
            itemview.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
                listener.onClickHq(position)
        }
    }

    interface OnClickHqListener{
        fun onClickHq(position: Int)
    }
}
