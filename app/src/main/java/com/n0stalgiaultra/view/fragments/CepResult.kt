package com.n0stalgiaultra.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.myapplication.databinding.FragmentCepResultBinding
import com.n0stalgiaultra.view.adapters.CardAdapter
import com.n0stalgiaultra.view.adapters.CardOnClick
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class CepResult : Fragment(), CardOnClick {

    private val mainViewModel: MainViewModel by activityViewModel()

    private lateinit var binding: FragmentCepResultBinding
    private val cardAdapter = CardAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCepResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.cepList.observe(viewLifecycleOwner){
            items ->
                if(items.isNotEmpty()){
                    cardAdapter.clearData(false)
                    cardAdapter.setData(items)
                    setupRecyclerView()
                    binding.loadingScreen.visibility = View.INVISIBLE
                }
                else{
                    Log.d("CepResult", "Lista Vazia")
                }
        }
    }

    private fun setupRecyclerView(){
        binding.mainRecView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = cardAdapter
        }
    }

    override suspend fun favoriteItem(item: CepDto) {
        mainViewModel.favoriteCep(item)
    }

    override suspend fun unFavoriteItem(item: CepDto) {
        mainViewModel.removeFavouriteCep()
    }

}