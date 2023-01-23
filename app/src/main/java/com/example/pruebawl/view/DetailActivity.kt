package com.example.pruebawl.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pruebawl.databinding.ActivityPoiDetailsBinding
import com.example.pruebawl.model.Poi

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPoiDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPoiDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val poi = getPoi()
        if (poi != null) {
            binding.poi = poi
            Glide.with(this)
                .load(poi.image)
                .into(binding.poiImage)
        } else {
            // Handle error if item is null
        }
    }

    private fun getPoi(): Poi? {

        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        val coord = intent.getStringExtra("coord")
        val image = intent.getStringExtra("image")

        if (name!= null && coord != null && image != null){
            return Poi(id, name, coord, image)
        }
        return null
    }
}
