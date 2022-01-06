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
import com.gink.palmkids.AppPref.token
import com.gink.palmkids.Extra
import com.gink.palmkids.R
import com.gink.palmkids.modelview.ModelViewPaymentStudent
import kotlinx.android.synthetic.main.activity_detail_berita.*
import kotlinx.android.synthetic.main.activity_tuition_student.*

class TuitionStudentActivity : AppCompatActivity() {
    lateinit var adapter : AdapterPaymet
    lateinit var mPayment: ModelViewPaymentStudent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tuition_student)

        initToolbar()

        mPayment = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewPaymentStudent::class.java
        )

        mPayment.dataPaymentStudent(token,this,progresbar_tuitionstudent)

        mPayment.getDataPaymentAuth().observe(this, androidx.lifecycle.Observer {
            if (it.isNotEmpty()){
                adapter.setDataPayment(it)
            }
        })

        adapter = AdapterPaymet {
            val intent = Intent(this,TuitioinDetailActivity::class.java)
            intent.putExtra(Extra,it)
            startActivity(intent)
        }

        recycleview_paymentstudent.isFocusable = true
        recycleview_paymentstudent.isClickable = true


        adapter.notifyDataSetChanged()
        val layoutmanager = LinearLayoutManager(this)
        layoutmanager.reverseLayout = true
        layoutmanager.stackFromEnd = true
        recycleview_paymentstudent.layoutManager  = layoutmanager
        recycleview_paymentstudent.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {

        toolbar_parent_tuitionStudent.setNavigationIcon(R.drawable.baseline_arrow_back_black_24)

        if (toolbar_parent_tuitionStudent.navigationIcon != null) {
            toolbar_parent_tuitionStudent.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }



        try {
            toolbar_parent_tuitionStudent.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar)
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
        toolbar_parent_tuitionStudent.setOnClickListener {
            finish()
        }
    }
}