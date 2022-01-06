package com.gink.palmkids.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gink.palmkids.R
import com.gink.palmkids.model.SubjectModel

class AdapterSubject(private val context: Context, davidoFallModelList :List<SubjectModel>, val itemclik: (SubjectModel) -> Unit)
    :RecyclerView.Adapter<AdapterSubject.viewholder>(){

    private val davidoFallModelList :List<SubjectModel> = davidoFallModelList
    private var davidoFallFiltered : List<SubjectModel> = davidoFallModelList


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_activity_subject,p0,false)
        return viewholder(view, itemclik)
    }

    override fun getItemCount(): Int {
        return davidoFallFiltered.count()
    }

    override fun onBindViewHolder(viewholder: viewholder, position: Int) {
        viewholder.bind_disney_songs(davidoFallFiltered[position],context)
    }

    class viewholder(itemview:View, val itemclik: (SubjectModel) -> Unit) :
        RecyclerView.ViewHolder(itemview){
        val judul_lagu =itemview.findViewById<TextView>(R.id.list_subject_matapelajaran)
        val penyanyi_lagu = itemview.findViewById<TextView>(R.id.list_subject_jadwal)

        fun bind_disney_songs(homeBeritaModel: SubjectModel, context: Context){
            judul_lagu.text = homeBeritaModel.judul
            penyanyi_lagu.text = homeBeritaModel.jam
            itemView.setOnClickListener{itemclik(homeBeritaModel)}
        }
    }
}


