package com.example.bisindo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class kamusAdapter(private val kamusList: ArrayList<kamusModel>) : RecyclerView.Adapter<kamusAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val IVgambar: ImageView = itemView.findViewById(R.id.imgDict)
        val TVhuruf: TextView = itemView.findViewById(R.id.txtDict)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_alfabet, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return kamusList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentKamus = kamusList[position]
        Picasso.get().load(currentKamus.gambar).into(holder.IVgambar)
        holder.TVhuruf.text = currentKamus.huruf

    }
}