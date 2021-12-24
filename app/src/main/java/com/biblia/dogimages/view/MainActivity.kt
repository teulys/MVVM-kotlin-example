package com.biblia.dogimages.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.biblia.dogimages.databinding.ActivityMainBinding
import com.biblia.dogimages.view.adapter.RVDogsAdapter
import com.biblia.dogimages.viewmodel.DogViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private var dogImages = mutableListOf<String>()
    private lateinit var adapter: RVDogsAdapter
    private lateinit var vmDog : DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.svDogSearch.setOnQueryTextListener(this)

        vmDog = ViewModelProviders.of(this).get(DogViewModel::class.java)

        vmDog.getErrorMessage().observe(this, Observer {
            showErrorMessege(it)
        })

        initRecycleView()
    }

    private fun initRecycleView() {
        adapter = RVDogsAdapter(dogImages)
        binding.rvDogsList.layoutManager = LinearLayoutManager(this)
        binding.rvDogsList.adapter = adapter
    }

    private fun showErrorMessege(errorMsg: String) {
        if (!errorMsg.isNullOrEmpty()) {
            Toast.makeText(this,errorMsg,Toast.LENGTH_LONG).show()
        }
    }

    private fun searchByName(breed: String) {
        CoroutineScope(Dispatchers.IO).launch{

            val dogliveData = vmDog.getDogsByBreends(breed)
            runOnUiThread {
                dogliveData?.observe(this@MainActivity, Observer {
                    dogImages.clear()
                    dogImages.addAll(it)

                    if (adapter == null) {
                        adapter = RVDogsAdapter(dogImages)
                    }

                    adapter.notifyDataSetChanged()
                })
            }
        }
    }

    override fun onQueryTextSubmit(text: String?): Boolean {
        if (!text.isNullOrEmpty()) {
            searchByName(text)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}
