package com.digitalhouse.desafiowebservice.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.digitalhouse.desafiowebservice.R
import com.digitalhouse.desafiowebservice.entities.Image
import com.digitalhouse.desafiowebservice.entities.Thumbnail
import kotlinx.android.synthetic.main.fragment_card_detail.*
import kotlinx.android.synthetic.main.fragment_card_detail.view.*
import kotlinx.android.synthetic.main.item_hqs.view.*
import java.time.LocalDate
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

            val inputFormatter = DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd'T'HH:mm:ss-SSSS",
                    Locale.ENGLISH
            );
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

            cardDetail.setOnClickListener { view ->
                imageViewDialog("${it.path}.${it.extension}")
            }

        }



        view.back_main.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }

    fun imageViewDialog(url: String){
        val myDialog = context?.let { Dialog(it, R.style.ThemeOverlay_AppCompat_Dark_ActionBar) }

        myDialog?.setContentView(R.layout.image_view_screen)
        myDialog?.setTitle("Image")

        val button = myDialog?.findViewById<ImageView>(R.id.btn_close_dialog)
        val image = myDialog?.findViewById<ImageView>(R.id.iv_image_dialog)

        button?.setOnClickListener {
            myDialog.cancel()
        }

        context?.let {
            if (image != null) {
                Glide.with(it).asBitmap()
                        .load(url)
                        .into(image)
            }
        }



        myDialog?.show()

    }






}