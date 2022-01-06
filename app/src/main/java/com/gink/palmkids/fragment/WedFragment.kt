package com.gink.palmkids.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gink.palmkids.R
import com.gink.palmkids.adapter.AdapterJadwal
import com.gink.palmkids.modelview.ModelViewJadwal
import com.gink.palmkids.utils.Utils
import kotlinx.android.synthetic.main.fragment_wed.*


//wednesday
class WedFragment : Fragment() {
    lateinit var adapter : AdapterJadwal
    lateinit var dataJadwal: ModelViewJadwal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = arguments?.getString(ARG_NAME)

        dataJadwal = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewJadwal::class.java
        )
        adapter = AdapterJadwal {
            Utils.peringatan(context!!,"Jadwal Pelajaran")
        }
        dataJadwal.dataJadwal(token!!,context!!,"wednesday")
        dataJadwal.getDataJadwal().observe(this, androidx.lifecycle.Observer {
            adapter.setDataJadwal(it)
        })
        adapter.notifyDataSetChanged()

        recycleview_wed.layoutManager = LinearLayoutManager(context)
        recycleview_wed.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_wed, container, false)
    }

    companion object {
        const val ARG_NAME = "DATA"
        fun newHomeInstance(token: String): WedFragment {
            val fragment = WedFragment()
            val args = Bundle()
            args.putString(ARG_NAME, token)
            fragment.arguments = args
            return fragment
        }
    }
}