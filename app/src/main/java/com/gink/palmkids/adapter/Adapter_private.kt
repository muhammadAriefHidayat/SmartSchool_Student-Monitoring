package com.gink.palmkids.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gink.palmkids.R
import com.gink.palmkids.model.Forum
import com.gink.palmkids.model.Private

class Adapter_private(val itemclik: (Private) -> Unit)
    : RecyclerView.Adapter<Adapter_private.viewholder>(){

    fun setDataForum(items:ArrayList<Private>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    private val mData = ArrayList<Private>()

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

    class viewholder(itemview: View, val itemclik: (Private) -> Unit) :
        RecyclerView.ViewHolder(itemview){
        val title =itemview.findViewById<TextView>(R.id.title_forum)
        val type =itemview.findViewById<TextView>(R.id.closed)
        val tanggal = itemview.findViewById<TextView>(R.id.tgl_forum)

        fun bind_disney_songs(homeBeritaModel: Private){
            val tipe = homeBeritaModel.type
            if (tipe == "1"){
                type.text = "Class"
            }else{
                type.text = "Private"
            }
            title.text = homeBeritaModel.text
            tanggal.text = homeBeritaModel.created
            itemView.setOnClickListener{itemclik(homeBeritaModel)}
        }
    }
}



