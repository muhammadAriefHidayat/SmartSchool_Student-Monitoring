package com.gink.palmkids.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gink.palmkids.R
import com.gink.palmkids.model.Jadwal

class AdapterJadwal(val itemclik: (Jadwal) -> Unit)
    : RecyclerView.Adapter<AdapterJadwal.viewholder>(){

    fun setDataJadwal(items:ArrayList<Jadwal>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    private val mData = ArrayList<Jadwal>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_jadwal,p0,false)
        return viewholder(view, itemclik)
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(viewholder: viewholder, position: Int) {
        viewholder.bind_disney_songs(mData[position])
    }

    class viewholder(itemview: View, val itemclik: (Jadwal) -> Unit) :
        RecyclerView.ViewHolder(itemview){
        val mapel =itemview.findViewById<TextView>(R.id.list_subject_matapelajaran)
        val jadwal = itemview.findViewById<TextView>(R.id.list_subject_jadwal)

        fun bind_disney_songs(homeBeritaModel: Jadwal){
            mapel.text = homeBeritaModel.mapel
            jadwal.text = homeBeritaModel.jam
            itemView.setOnClickListener{itemclik(homeBeritaModel)}
        }
    }
}


