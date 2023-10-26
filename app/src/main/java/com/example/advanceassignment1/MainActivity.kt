package com.example.advanceassignment1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advanceassignment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[ContactViewModel::class.java] }
    private lateinit var itemContactAdapter: ItemContactAdapter


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rv.layoutManager = LinearLayoutManager(this)
        itemContactAdapter = ItemContactAdapter(emptyList())
        binding.rv.adapter = itemContactAdapter
        viewModel.contacts.observe(this) {
            itemContactAdapter.updateList(it)
        }
    }
}