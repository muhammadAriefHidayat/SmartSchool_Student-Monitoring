package com.gink.palmkids.activity

import AdapterPaymet
import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gink.palmkids.Extra
import com.gink.palmkids.R
import com.gink.palmkids.modelview.ModelViewPayment
import kotlinx.android.synthetic.main.activity_tuition.*

class  TuitionActivity : AppCompatActivity() {
    lateinit var adapter : AdapterPaymet
    lateinit var mPayment: ModelViewPayment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tuition)
        val token = intent.getStringExtra(Extra)
        initToolbar()

        mPayment = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewPayment::class.java
        )

        mPayment.dataPayment(token!!,this,progresbar_tuition)

        mPayment.getDataPayment().observe(this, androidx.lifecycle.Observer {
            if (it.isNotEmpty()){
                adapter.setDataPayment(it)
            }
        })

        adapter = AdapterPaymet {
            val intent = Intent(this,TuitioinDetailActivity::class.java)
            intent.putExtra(Extra,it)
            startActivity(intent)
        }

        recycleview_payment.isFocusable = true
        recycleview_payment.isClickable = true


        adapter.notifyDataSetChanged()
        val linearLayout = LinearLayoutManager(this)
        linearLayout.reverseLayout = true
        linearLayout.stackFromEnd = true
        recycleview_payment.layoutManager = linearLayout
        recycleview_payment.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {
        toolbar_parent_tuition.setNavigationIcon(R.drawable.baseline_arrow_back_black_24)

        if (toolbar_parent_tuition.navigationIcon != null) {
            toolbar_parent_tuition.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }
        toolbar_parent_tuition.title = ""
        try {
            toolbar_parent_tuition.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar_parent_tuition)
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
        toolbar_parent_tuition.setOnClickListener {
            finish()
        }
    }
}