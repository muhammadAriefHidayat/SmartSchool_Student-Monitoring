package com.gink.palmkids.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gink.palmkids.Extra
import com.gink.palmkids.Newextra
import com.gink.palmkids.R
import com.gink.palmkids.adapter.Adapter_forum
import com.gink.palmkids.modelview.ModelViewForum
import com.gink.palmkids.modelview.ModelViewForumAdmin
import com.gink.palmkids.utils.Utils
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    lateinit var forum : ModelViewForum
    lateinit var forumAdmin : ModelViewForumAdmin
    lateinit var adapter : Adapter_forum

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        initToolbar()

        val token = intent.getStringExtra(Extra)
        forum = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewForum::class.java
        )

        question_addnew.setOnClickListener {
            getCustomDialogTopic(R.layout.dialog_forum,R.color.colorAccent,token)
        }

        forumAdmin = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            ModelViewForumAdmin::class.java
        )

        val adapter = Adapter_forum{
            val intent = Intent(this, QuestionReplyActivity::class.java)
            intent.putExtra(Extra, it)
            intent.putExtra(Newextra, token)
            startActivity(intent)
        }

        swipe_refresh.setOnRefreshListener {
            adapter.notifyDataSetChanged()
            forumAdmin.setForumAdmin(token,this,queestion_progressbar)
            forumAdmin.getDataForumAdmin().observe(this, Observer {
                if (it.isNotEmpty()){
                    adapter.setDataForum(it)
                }
            })
            swipe_refresh.isRefreshing = false
        }

        adapter.notifyDataSetChanged()
        forumAdmin.setForumAdmin(token,this,queestion_progressbar)
        forumAdmin.getDataForumAdmin().observe(this, Observer {
            if (it.isNotEmpty()){
                adapter.setDataForum(it)
            }
        })
        recycleview_forumlist.scrollToPosition(0)
        val linearLayout = LinearLayoutManager(this)
        linearLayout.stackFromEnd = true
        linearLayout.reverseLayout = true
        recycleview_forumlist.layoutManager = linearLayout
        recycleview_forumlist.adapter = adapter
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {

        toolbar_question.setNavigationIcon(R.drawable.baseline_arrow_back_black_24)

        if (toolbar_question.navigationIcon != null) {
            toolbar_question.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar_question.title = "Forum"

        try {
            toolbar_question.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar_question)
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
        toolbar_question.setOnClickListener {
            finish()
        }
    }

    private fun getCustomDialogTopic(layoutId: Int, colorId: Int, token: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutId)

        val lp = WindowManager.LayoutParams()
        if (dialog.window != null) {

            lp.copyFrom(dialog.window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            val topic = dialog.findViewById<EditText>(R.id.dialog_pencatatan_topic)
            val desc = dialog.findViewById<EditText>(R.id.dialog_pencatatan_dec)


            val positiveButton = dialog.findViewById<Button>(R.id.dialog_pencatatan_simpan)
            positiveButton.setOnClickListener {
                dialog.cancel()

                val topic_text = topic.text.toString()
                val desc_text = desc.text.toString()
                if (topic_text.isEmpty() or desc_text.isEmpty()){
                    Utils.peringatan(this,"topic atau desc tidak boleh kosong")
                }else{
                    forum.setForum(topic_text,desc_text,token!!,this,queestion_progressbar)
                }
            }

            dialog.show()
            dialog.window?.attributes = lp
        }
    }
}