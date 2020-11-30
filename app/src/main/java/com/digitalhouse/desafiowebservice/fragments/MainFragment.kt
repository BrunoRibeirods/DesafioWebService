package com.digitalhouse.desafiowebservice.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.desafiowebservice.R
import com.digitalhouse.desafiowebservice.adapters.HqsAdapter
import com.digitalhouse.desafiowebservice.entities.Hqs
import com.digitalhouse.desafiowebservice.service.service
import com.digitalhouse.desafiowebservice.ui.MainViewModel

class MainFragment : Fragment(), HqsAdapter.OnClickHqListener {
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var adapter: HqsAdapter
    lateinit var comic: Hqs
    lateinit var bundle: Bundle

    val viewModel by viewModels<MainViewModel>(){
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(service) as T
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        viewModel.getAllHqsModel()

        val recycler = view.findViewById<RecyclerView>(R.id.rc_main_activity)
        val list = mutableListOf<Hqs>()

        adapter = HqsAdapter(list, this)
        gridLayoutManager = GridLayoutManager(context, 3)
        recycler.adapter = adapter
        recycler.layoutManager = gridLayoutManager
        recycler.hasFixedSize()

        viewModel.listaHqs.observe(viewLifecycleOwner){
            recycler.adapter = HqsAdapter(it, this)
            Log.i("Verdadeiro", it.toString())

        }





        return view
    }

    override fun onClickHq(position: Int) {
        viewModel.listaHqs.observe(viewLifecycleOwner){
             comic = it[position]
        }
        adapter.notifyItemChanged(position)



        bundle = bundleOf("nome" to comic.title,
                "description" to comic.description,
                "page" to comic.pageCount.toString(),
                "price" to comic.prices[0].price.toString(),
                "date" to comic.dates[0].date,
                "thumb" to comic.thumbnail
        )





        findNavController().navigate(R.id.action_mainFragment_to_cardDetailFragment, bundle)
    }


}