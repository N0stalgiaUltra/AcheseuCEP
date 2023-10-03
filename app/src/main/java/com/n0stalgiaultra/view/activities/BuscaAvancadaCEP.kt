package com.n0stalgiaultra.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.ActivityBuscaAvancadaCepBinding
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BuscaAvancadaCEP : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityBuscaAvancadaCepBinding
    private var state: String = ""
    private var city: String = ""
    private var street: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscaAvancadaCepBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        binding.btnBuscaCEP.setOnClickListener {
            state = binding.etEstado.text.toString()
            city = binding.etCidade.text.toString()
            street = binding.etRua.text.toString()

            CoroutineScope(Dispatchers.Main).launch {
                mainViewModel.getCepList(state, city, street)

            }
        }
    }
}