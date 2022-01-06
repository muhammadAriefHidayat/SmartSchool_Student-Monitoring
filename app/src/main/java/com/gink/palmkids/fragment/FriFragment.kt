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
import kotlinx.android.synthetic.main.fragment_fri.*


class FriFragment : Fragment() {
    lateinit var adapter : AdapterJadwal
    lateinit var dataJadwal: ModelViewJadwal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = arguments?.getString(WedFragment.ARG_NAME)

        dataJadwal = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewJadwal::class.java
        )
        adapter = AdapterJadwal {Utils.peringatan(context!!,"Jadwal Pelajaran")}
        dataJadwal.dataJadwal(token!!,context!!,"friday")
        dataJadwal.getDataJadwal().observe(this, androidx.lifecycle.Observer {
            adapter.setDataJadwal(it)
        })
        adapter.notifyDataSetChanged()

        recycleveiw_fri.layoutManager = LinearLayoutManager(context)
        recycleveiw_fri.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fri, container, false)
    }

    companion object {
        const val ARG_NAME = "DATA"
        fun newHomeInstance(token: String): FriFragment {
            val fragment = FriFragment()
            val args = Bundle()
            args.putString(ARG_NAME, token)
            fragment.arguments = args
            return fragment
        }
    }
}
