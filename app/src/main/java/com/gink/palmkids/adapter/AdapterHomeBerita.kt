package com.gink.palmkids.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gink.palmkids.R
import com.gink.palmkids.model.HomeBeritaModel

class AdapterHomeBerita(val itemclik: (HomeBeritaModel) -> Unit)
    :RecyclerView.Adapter<AdapterHomeBerita.viewholder>(){

    fun setDataBerita(items:ArrayList<HomeBeritaModel>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    private val mData = ArrayList<HomeBeritaModel>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_activity_home,p0,false)
        return viewholder(view, itemclik)
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(viewholder: viewholder, position: Int) {
        viewholder.bind_disney_songs(mData[position])
    }

    class viewholder(itemview:View, val itemclik: (HomeBeritaModel) -> Unit) :
        RecyclerView.ViewHolder(itemview){
        val judul_lagu =itemview.findViewById<TextView>(R.id.list_home_judulberita)
        val davido_logo = itemview.findViewById<ImageView>(R.id.list_home_image)
        val penyanyi_lagu = itemview.findViewById<TextView>(R.id.list_tanggal_judulberita)

        fun bind_disney_songs(homeBeritaModel: HomeBeritaModel){
//            val resoircegambar = context.resources.getIdentifier(homeBeritaModel.,"drawable",
//                context.packageName)
//            davido_logo.setImageResource(resoircegambar)
            judul_lagu.text = homeBeritaModel.title
            penyanyi_lagu.text = homeBeritaModel.created
            itemView.setOnClickListener{itemclik(homeBeritaModel)}
        }
    }
}


