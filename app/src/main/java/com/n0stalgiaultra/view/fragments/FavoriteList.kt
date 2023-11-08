package com.n0stalgiaultra.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.myapplication.databinding.FragmentFavoriteListBinding
import com.n0stalgiaultra.view.adapters.CardAdapter
import com.n0stalgiaultra.view.adapters.CardOnClick
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class FavoriteList : Fragment(), CardOnClick {

    private val mainViewModel: MainViewModel by activityViewModel()
    private lateinit var binding: FragmentFavoriteListBinding

    private val cardAdapter = CardAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getLocalData()


    }

    override fun onResume() {
        super.onResume()

        mainViewModel.localCepList.observe(viewLifecycleOwner){
            favItems ->
            if(favItems.isNotEmpty()){
                Log.d("FavList", "${favItems[0].logradouro}")

                cardAdapter.clearData()
                cardAdapter.setLocalData(favItems)
                setupRecyclerView()
                binding.favEmptyText.visibility = View.INVISIBLE
                binding.loadingScreen.visibility = View.INVISIBLE

            }
            else{
                binding.loadingScreen.visibility = View.INVISIBLE
                Log.d("FavList", "Empty")

            }
        }
    }

    private fun setupRecyclerView(){
        binding.favRecView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = cardAdapter
        }
    }


    /*
        TODO: Precisa mesmo disso aqui?
         Será que não seria melhor implementar dentro do VM?
     */
    override suspend fun favoriteItem(item: CepDto) {
        TODO("Not yet implemented")
    }

    override suspend fun unFavoriteItem(item: CepLocal) {
        TODO("Not yet implemented")
    }
}