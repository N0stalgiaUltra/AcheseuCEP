package com.n0stalgiaultra.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.utils.FragmentIdHandler
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var tabLayout: TabLayout
    private lateinit var idHandler : FragmentIdHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tab_layout)
        idHandler = FragmentIdHandler(this)
    }

    override fun onResume() {
        super.onResume()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            val navController = findNavController(R.id.nav_host_fragment)
            val navInflater = navController.navInflater
            override fun onTabSelected(tab: TabLayout.Tab?) {

                val navGraphID =
                    if(tab?.position == 0) R.navigation.nav_graph_busca
                    else R.navigation.nav_graph_fav

                /*Inflar: criar uma instancia de um arq XML */
                val navGraph = navInflater.inflate(navGraphID)
                navController.graph = navGraph

                if(tab?.position == 0)
                    navController.navigate(idHandler.getID())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}