package com.gink.palmkids.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gink.palmkids.R
import com.gink.palmkids.model.Forum

class Adapter_forum(val itemclik: (Forum) -> Unit)
    : RecyclerView.Adapter<Adapter_forum.viewholder>(){

    fun setDataForum(items:ArrayList<Forum>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    private val mData = ArrayList<Forum>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_forum,p0,false)
        return viewholder(view, itemclik)
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(viewholder: viewholder, position: Int) {
        viewholder.bind_disney_songs(mData[position])
    }

    class viewholder(itemview: View, val itemclik: (Forum) -> Unit) :
        RecyclerView.ViewHolder(itemview){
        val title =itemview.findViewById<TextView>(R.id.title_forum)
        val desc = itemview.findViewById<TextView>(R.id.desc_forum)
        val tanggal = itemview.findViewById<TextView>(R.id.tgl_forum)

        fun bind_disney_songs(homeBeritaModel: Forum){
            title.text = homeBeritaModel.title
            desc.text = homeBeritaModel.description
            tanggal.text = homeBeritaModel.created
            itemView.setOnClickListener{itemclik(homeBeritaModel)}
        }
    }
}



