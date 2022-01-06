package com.gink.palmkids.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gink.palmkids.Extra
import com.gink.palmkids.Newextra
import com.gink.palmkids.R
import com.gink.palmkids.Tipeextra
import com.gink.palmkids.activity.PrivateComReplyActivity
import com.gink.palmkids.adapter.Adapter_private
import com.gink.palmkids.modelview.ModelViewPrivate
import com.gink.palmkids.utils.Utils
import kotlinx.android.synthetic.main.fragment_class.*

class ClassFragment : Fragment() {

    lateinit var privateBook : ModelViewPrivate
    lateinit var adapter : Adapter_private

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = arguments?.getString(PrivateFragment.ARG_NAME)
        privateBook = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewPrivate::class.java
        )

        adapter = Adapter_private {
            val tipe = "compbookgetreply"
            val intent = Intent(context, PrivateComReplyActivity::class.java)
            intent.putExtra(Extra,it)
            intent.putExtra(Tipeextra,tipe)
            intent.putExtra(Newextra,token)
            startActivity(intent)
        }

        privateBook.setPrivateBook(token!!,context!!,private_progressbar,"compbookclass")
        privateBook.getDataPrivatebook().observe(this, androidx.lifecycle.Observer {
            adapter.setDataForum(it)
        })

        adapter.notifyDataSetChanged()
        val layoutmanager = LinearLayoutManager(context)
        layoutmanager.reverseLayout = true
        layoutmanager.stackFromEnd = true
        recycleview_forumlist.layoutManager = layoutmanager
        recycleview_forumlist.adapter = adapter
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_class, container, false)
    }

    companion object {
        const val ARG_NAME = "DATA"
        fun newHomeInstance(token: String): ClassFragment {
            val fragment = ClassFragment()
            val args = Bundle()
            args.putString(ARG_NAME, token)
            fragment.arguments = args
            return fragment
        }
    }
}