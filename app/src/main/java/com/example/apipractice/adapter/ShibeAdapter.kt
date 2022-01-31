package com.example.apipractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apipractice.R
import com.example.apipractice.databinding.ItemshibeBinding

class ShibeAdapter(private  val urls: List<String>): RecyclerView.Adapter<ShibeAdapter.ShibeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ) = ShibeViewHolder.newInstances(parent)

    class ShibeViewHolder(
        private val binding: ItemshibeBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bindUrl(url: String){
            binding.tvUrls.text = url
            Glide.with(itemView)
                .load(url)
                .into(binding.imgUrl)
        }
        companion object {
            fun newInstances(parent: ViewGroup) = ItemshibeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).let { ShibeViewHolder(it) }
        }
    }


    override fun onBindViewHolder(holder: ShibeViewHolder, position: Int) {
        holder.bindUrl(urls[position])
    }

    override fun getItemCount() = urls.size
}