package com.digitalhouse.desafiowebservice.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.digitalhouse.desafiowebservice.R
import com.digitalhouse.desafiowebservice.entities.ComicDate
import com.digitalhouse.desafiowebservice.entities.Data
import com.digitalhouse.desafiowebservice.entities.Image
import com.digitalhouse.desafiowebservice.entities.Thumbnail
import kotlinx.android.synthetic.main.fragment_card_detail.*
import kotlinx.android.synthetic.main.fragment_card_detail.view.*
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class CardDetailFragment : Fragment() {


    @SuppressLint("NewApi")
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
            view.tv_price.text = " $" + it
        }

        arguments?.getString("dates")?.let {


            //val odt = OffsetDateTime.parse ( it , DateTimeFormatter.ofPattern ( "yyyy-MM-dd'T'HH:mm:ss-SSSS", Locale.ENGLISH))

            val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss-SSSS", Locale.ENGLISH);
            val outputFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH)
            val date = LocalDate.parse(it, inputFormatter);
            val formattedDate = outputFormatter.format(date);

            view.tv_date_detail.text = formattedDate.toString()
        }





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