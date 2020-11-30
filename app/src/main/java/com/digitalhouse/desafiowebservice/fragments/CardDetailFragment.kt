package com.digitalhouse.desafiowebservice.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.digitalhouse.desafiowebservice.R
import com.digitalhouse.desafiowebservice.entities.Image
import com.digitalhouse.desafiowebservice.entities.Thumbnail
import kotlinx.android.synthetic.main.fragment_card_detail.*
import kotlinx.android.synthetic.main.fragment_card_detail.view.*
import java.text.SimpleDateFormat
import java.util.*


class CardDetailFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_card_detail, container, false)

        arguments?.getString("nome")?.let {
            view.tv_title_detail.text = it
        }
        arguments?.getString("description")?.let {
            view.tv_description.text = it
        }

        arguments?.getString("page")?.let {
            view.tv_page.text = it
        }
        arguments?.getString("price")?.let {
            view.tv_price.text = it
        }
//        arguments?.getString("date")?.let {
//            view.tv_date_detail.text = it
//        }

        arguments?.getSerializable("thumb")?.let {
            it as Thumbnail

            val thumb = view.findViewById<ImageView>(R.id.iv_thumbnail)

            context?.let { it1 ->
                Glide.with(it1).asBitmap()
                        .load("${it.path}.${it.extension}")
                        .into(thumb)
            }
        }


        arguments?.getSerializable("images")?.let {
                it as Image


            val cardDetail = view.findViewById<ImageView>(R.id.item_image_hqs)


            context?.let { it1 ->
                Glide.with(it1).asBitmap()
                        .load("${it.path}.${it.extension}")
                        .into(cardDetail)
            }


        }





        view.back_main.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }



}