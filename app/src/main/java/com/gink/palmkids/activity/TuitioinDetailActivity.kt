package com.gink.palmkids.activity

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.gink.palmkids.Extra
import com.gink.palmkids.R
import com.gink.palmkids.model.Payment
import kotlinx.android.synthetic.main.activity_detail_berita.*
import kotlinx.android.synthetic.main.activity_detail_tuitioin.*
import kotlinx.android.synthetic.main.activity_detail_tuitioin.ninini

class TuitioinDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tuitioin)
        initToolbar()

         val item  =  intent.getParcelableExtra<Payment>(Extra)
        ninini.text = item!!.text
        tanggal_value.text = item.date
        rincianvalue.text = item.jumlah_bayar

        Log.d("itempay",item.date.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {

        toolbar_tuition.setNavigationIcon(R.drawable.baseline_arrow_back_black_24)

        if (toolbar_tuition.navigationIcon != null) {
            toolbar_tuition.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }



        try {
            toolbar_tuition.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
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
        toolbar_tuition.setOnClickListener {
            finish()
        }
    }

}