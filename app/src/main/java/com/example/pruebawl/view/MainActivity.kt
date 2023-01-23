package com.example.pruebawl.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.pruebawl.databinding.ActivityMainBinding
import com.example.pruebawl.model.Poi
import com.example.pruebawl.view.adapter.PoiAdapter
import com.example.pruebawl.viewmodel.ItemViewModelFactory
import com.example.pruebawl.viewmodel.PoiViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PoiViewModel
    private var adapter = PoiAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ItemViewModelFactory(application)
        viewModel = ViewModelProvider(this,factory)[PoiViewModel::class.java]
        binding.rvActivityMain.adapter = adapter
        viewModel.poiList.observe(this) {
            Log.d("DESESPERACION", it.toString())
            adapter.submitList(it?.toMutableList())
        }
        adapter.setOnItemClickListener(object : PoiAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                viewModel.poiList.value?.get(position)?.let { startDetailActivity(it) }
            }
        })
    }

    fun startDetailActivity(poi: Poi) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name", poi.name)
        intent.putExtra("coord", poi.geocoordinates)
        intent.putExtra("image", poi.image)
        intent.putExtra("id", poi.id)
        startActivity(intent)
    }

}