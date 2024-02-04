package com.n0stalgiaultra.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.n0stalgiaultra.myapplication.databinding.FragmentFavoriteListBinding
import com.n0stalgiaultra.view.adapters.CardAdapter
import com.n0stalgiaultra.view.adapters.CardOnClickImpl
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteList : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModel()
    private lateinit var binding: FragmentFavoriteListBinding

    private val cardOnClick by lazy { CardOnClickImpl(mainViewModel) }
    private val cardAdapter by lazy { CardAdapter(cardOnClick, isLocal = true) }


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
        mainViewModel.getFavoriteItems()
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.localCepList.observe(viewLifecycleOwner){
            favItems ->
            Log.d("FavItems", favItems.size.toString())
            if(favItems.isNotEmpty()){
                cardAdapter.setLocalData(favItems)
                setupRecyclerView()
                binding.favEmptyText.visibility = View.INVISIBLE
                binding.loadingScreen.visibility = View.INVISIBLE

            }
            else{
                binding.loadingScreen.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupRecyclerView(){
        binding.favRecView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = cardAdapter
        }
    }

}