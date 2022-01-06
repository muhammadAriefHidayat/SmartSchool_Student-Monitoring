package com.gink.palmkids.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gink.palmkids.R
import com.gink.palmkids.model.Reply
import com.gink.palmkids.model.ReplyPrivate

class Adapter_private_reply: RecyclerView.Adapter<Adapter_private_reply.viewholder>(){

    fun setDataReply(items:ArrayList<ReplyPrivate>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    private val mData = ArrayList<ReplyPrivate>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_chat_reply,p0,false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(viewholder: viewholder, position: Int) {
        viewholder.bind_disney_songs(mData[position])
    }

    class viewholder(itemview: View) :
        RecyclerView.ViewHolder(itemview){
        val title =itemview.findViewById<TextView>(R.id.text_reply)
        val tanggal = itemview.findViewById<TextView>(R.id.tanggal_reply)
        val firstName = itemview.findViewById<TextView>(R.id.user_reply)

        fun bind_disney_songs(homeBeritaModel: ReplyPrivate){
            tanggal.text = homeBeritaModel.created
            title.text = homeBeritaModel.text
            firstName.text = homeBeritaModel.first_name
        }
    }
}



