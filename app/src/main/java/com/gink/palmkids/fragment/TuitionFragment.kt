package com.gink.palmkids.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gink.palmkids.R

class TuitionFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tuition, container, false)
    }

    companion object {
        const val ARG_NAME = "DATA"
        fun newHomeInstance(token: String): TueFragment {
            val fragment = TueFragment()
            val args = Bundle()
            args.putString(ARG_NAME, token)
            fragment.arguments = args
            return fragment
        }
    }
}