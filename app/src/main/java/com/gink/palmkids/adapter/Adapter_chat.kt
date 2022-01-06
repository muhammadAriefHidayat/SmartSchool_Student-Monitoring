package com.gink.palmkids.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gink.palmkids.R
import com.gink.palmkids.model.Reply

class Adapter_chat(val itemclik: (Reply) -> Unit)
    : RecyclerView.Adapter<Adapter_chat.viewholder>(){

    fun setDataReply(items:ArrayList<Reply>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    private val mData = ArrayList<Reply>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_chat,p0,false)
        return viewholder(view, itemclik)
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(viewholder: viewholder, position: Int) {
        viewholder.bind_disney_songs(mData[position])
    }

    class viewholder(itemview: View, val itemclik: (Reply) -> Unit) :
        RecyclerView.ViewHolder(itemview){
        val title =itemview.findViewById<TextView>(R.id.text_chat)
        val tanggal = itemview.findViewById<TextView>(R.id.tanggal_chat)

        fun bind_disney_songs(homeBeritaModel: Reply){
            title.text = homeBeritaModel.text
            tanggal.text = homeBeritaModel.created
            itemView.setOnClickListener{itemclik(homeBeritaModel)}
        }
    }
}



