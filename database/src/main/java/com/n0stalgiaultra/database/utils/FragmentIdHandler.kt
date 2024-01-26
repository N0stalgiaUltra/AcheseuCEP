package com.n0stalgiaultra.database.utils

import android.content.Context

class FragmentIdHandler(private val context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("BuscaCEP", Context.MODE_PRIVATE)

    fun saveID(fragementID: Int){
        sharedPreferences.edit().putInt("FragID", fragementID).apply()
    }

    fun getID(): Int{
        return sharedPreferences.getInt("FragID", 0)
    }
}