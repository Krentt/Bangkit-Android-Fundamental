package com.example.githubusersearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

class DetailProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_profil)

        supportActionBar?.title = "Detail User"

        val username =intent.getStringExtra("DATA")




    }
}