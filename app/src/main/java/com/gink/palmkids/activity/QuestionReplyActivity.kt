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
import com.pawegio.kandroid.textWatcher
import com.gink.palmkids.Extra
import com.gink.palmkids.Newextra
import com.gink.palmkids.R
import com.gink.palmkids.adapter.Adapter_reply
import com.gink.palmkids.model.Forum
import com.gink.palmkids.modelview.ModelViewReply
import com.gink.palmkids.modelview.ModelViewReplyAdmin
import com.gink.palmkids.utils.Utils
import kotlinx.android.synthetic.main.activity_question_reply.*

class QuestionReplyActivity : AppCompatActivity() {

    lateinit var reply: ModelViewReply
    lateinit var reply_Admin: ModelViewReplyAdmin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_reply)
        initToolbar()

        val data = intent.getParcelableExtra<Forum>(Extra)
        val token = intent.getStringExtra(Newextra)
        questioncreate_judulvalue.text = data!!.title
        questioncreate_tanggalTV.text = data.created

        reply_Admin = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewReplyAdmin::class.java
        )

        val adapter = Adapter_reply()
        refress(adapter, token!!, data.id)

        refres_reply.setOnRefreshListener {
            refress(adapter, token, data.id)
            refres_reply.isRefreshing = false
        }

        val linearLayout =  LinearLayoutManager(this)
//        linearLayout.reverseLayout = true
        linearLayout.stackFromEnd = true
        questionstatus_recyclerview.layoutManager = linearLayout
        questionstatus_recyclerview.adapter = adapter

        reply = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewReply::class.java
        )

        val chat = findViewById<TextView>(R.id.questioncreate_tulispesan)
        chat.textWatcher {
            afterTextChanged {
                btn_send.setOnClickListener {
                    reply.dataReply(token, data.id, chat.text.toString(), applicationContext)
                    chat.text = ""
                }
            }
            refress(adapter, token, data.id)
        }
        refress(adapter, token, data.id)
    }

    fun refress(adapter: Adapter_reply, token: String, id: String) {
        adapter.notifyDataSetChanged()
        reply_Admin.setDataReply(token, id, this)
        reply_Admin.getDataReply().observe(this, Observer {
            if (it.isNotEmpty()) {
                adapter.setDataReply(it)
            }
        })
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
