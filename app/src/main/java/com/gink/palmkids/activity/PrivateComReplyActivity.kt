package com.gink.palmkids.activity

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gink.palmkids.Extra
import com.gink.palmkids.Newextra
import com.gink.palmkids.R
import com.gink.palmkids.Tipeextra
import com.gink.palmkids.adapter.Adapter_private_reply
import com.gink.palmkids.adapter.Adapter_reply
import com.gink.palmkids.model.Forum
import com.gink.palmkids.model.Private
import com.gink.palmkids.model.ReplyPrivate
import com.gink.palmkids.modelview.ModelViewPrivateReply
import com.gink.palmkids.modelview.ModelViewPrivateReplyAdmin
import com.gink.palmkids.modelview.ModelViewReply
import com.gink.palmkids.modelview.ModelViewReplyAdmin
import com.gink.palmkids.utils.Utils
import com.pawegio.kandroid.textWatcher
import kotlinx.android.synthetic.main.activity_question_reply.*

class PrivateComReplyActivity : AppCompatActivity() {
    lateinit var reply: ModelViewPrivateReply
    lateinit var reply_Admin: ModelViewPrivateReplyAdmin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_com_reply)

        initToolbar()

        val data = intent.getParcelableExtra<Private>(Extra)
        val token = intent.getStringExtra(Newextra)
        val tipe = intent.getStringExtra(Tipeextra)

        questioncreate_judulvalue.text = data!!.text
        questioncreate_tanggalTV.text = data.created

        reply_Admin = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewPrivateReplyAdmin::class.java
        )

        val adapter = Adapter_private_reply()
        adapter.notifyDataSetChanged()
        reply_Admin.setDataReply(token!!, tipe!!,data.id, this)
        reply_Admin.getDataReply().observe(this, Observer {
            if (it.isNotEmpty()) {
                adapter.setDataReply(it)
            }
        })

        refres_reply.setOnRefreshListener {
            reply_Admin.getDataReply().observe(this, Observer {
                if (it.isNotEmpty()) {
                    adapter.setDataReply(it)
                }
            })
            adapter.notifyDataSetChanged()
            refres_reply.isRefreshing = false
        }

        val linearLayout =  LinearLayoutManager(this)
        linearLayout.stackFromEnd = true
        questionstatus_recyclerview.layoutManager = linearLayout
        questionstatus_recyclerview.adapter = adapter

        reply = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewPrivateReply::class.java
        )
        val chat = findViewById<TextView>(R.id.questioncreate_tulispesan)
        chat.textWatcher {
            afterTextChanged {
                btn_send.setOnClickListener {
                    reply.dataReply(token, data.id, chat.text.toString(), applicationContext)
                    chat.text = ""
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {

        toolbar_reply.setNavigationIcon(R.drawable.baseline_arrow_back_black_24)

        if (toolbar_reply.navigationIcon != null) {
            toolbar_reply.navigationIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.md_white_1000
                ), PorterDuff.Mode.SRC_ATOP
            )
        }

        toolbar_reply.title = ""

        try {
            toolbar_reply.setTitleTextColor(ContextCompat.getColor(this, R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar_reply)
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set support action bar.")
        }

        try {
            if (supportActionBar != null) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set display home as up enabled.")
        }
        toolbar_reply.setOnClickListener {
            finish()
        }
    }
}