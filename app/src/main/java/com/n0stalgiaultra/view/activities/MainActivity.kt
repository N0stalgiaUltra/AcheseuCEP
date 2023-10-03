package com.n0stalgiaultra.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.ActivityMainBinding
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.btnBuscapadraocep.setOnClickListener {
            val intent = Intent(applicationContext, BuscaPorCEPActivity::class.java)
            startActivity(intent)
        }

        binding.btnBuscaavancadaocep.setOnClickListener {
            val intent = Intent(applicationContext, BuscaAvancadaCEP::class.java)
            startActivity(intent)
        }

    }
    override fun onResume() {
        super.onResume()

//        mainViewModel.cepList.observe(this){
//            items ->
//            if(items.isEmpty())
//            {
//                Log.d("Activity", "empty list")
//
//            }
//            else{
//                Log.d("Activity", "Success")
//            }
//        }
//
//        CoroutineScope(Dispatchers.Main).launch{
//            mainViewModel.getCepList()
//        }
    }
}