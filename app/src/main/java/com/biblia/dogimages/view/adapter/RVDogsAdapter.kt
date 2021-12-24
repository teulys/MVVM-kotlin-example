package com.biblia.dogimages.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biblia.dogimages.R
import com.biblia.dogimages.databinding.ItemDogsBinding
import com.squareup.picasso.Picasso

class RVDogsAdapter(private val images: List<String>) : RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dogs,parent,false))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}

class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemDogsBinding.bind(itemView)

    fun bind(image:String){
        Picasso.get().load(image).into(binding.ivDogs)
    }

}
