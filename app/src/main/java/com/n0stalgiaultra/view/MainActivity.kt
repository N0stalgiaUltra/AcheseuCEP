package com.n0stalgiaultra.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.cepList.observe(this){
            items ->
            if(items.isEmpty())
            {
                Log.d("Activity", "empty list")

            }
            else{
                Log.d("Activity", "Success")
            }
        }

        CoroutineScope(Dispatchers.Main).launch{
            mainViewModel.getCepList()
        }
    }
}