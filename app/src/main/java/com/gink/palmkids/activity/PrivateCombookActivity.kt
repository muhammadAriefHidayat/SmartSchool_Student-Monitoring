package com.gink.palmkids.activity

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.gink.palmkids.Extra
import com.gink.palmkids.R
import com.gink.palmkids.fragment.ClassFragment
import com.gink.palmkids.fragment.MondayFragment
import com.gink.palmkids.fragment.PrivateFragment
import com.gink.palmkids.fragment.TueFragment
import com.gink.palmkids.utils.Utils
import kotlinx.android.synthetic.main.activity_private_combook.*
import kotlinx.android.synthetic.main.activity_subject.*

class PrivateCombookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_combook)

        val token = intent.getStringExtra(Extra)

        initToolbar()

        private(true, token!!)

        private_combook.setOnClickListener {
            private(true, token)
            class_com(false,token)
        }

        class_combook.setOnClickListener {
            class_com(true,token)
            private(false, token)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {
        toolbar_questionprivate.setNavigationIcon(R.drawable.baseline_arrow_back_black_24)
        if (toolbar_questionprivate.navigationIcon != null) {
            toolbar_questionprivate.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar_questionprivate.title = "Combook"

        try {
            toolbar_questionprivate.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar_questionprivate)
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
        toolbar_questionprivate.setOnClickListener {
            finish()
        }
    }

    private fun setupFragment(fragment: Fragment) {
        try {
            this.supportFragmentManager.beginTransaction()
                .replace(R.id.frame_combook, fragment)
                .commitAllowingStateLoss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun private(clicked: Boolean, token: String){
        if (clicked){
            private_combook.setBackgroundResource(R.drawable.bakcgroud_rounded_pink)
            private_combook.setTextColor(ContextCompat.getColor(applicationContext, R.color.md_white_1000));
            setupFragment(PrivateFragment.newHomeInstance(token))
        }else{
            private_combook.setBackgroundResource(R.color.md_white_1000)
            private_combook.setTextColor(ContextCompat.getColor(applicationContext, R.color.tulisanHitam));
        }
    }

    fun class_com(clicked: Boolean, token: String){
        if (clicked){
            class_combook.setBackgroundResource(R.drawable.bakcgroud_rounded_pink)
            class_combook.setTextColor(ContextCompat.getColor(applicationContext, R.color.md_white_1000));
            setupFragment(ClassFragment.newHomeInstance(token))
        }else{
            class_combook.setBackgroundResource(R.color.md_white_1000)
            class_combook.setTextColor(ContextCompat.getColor(applicationContext, R.color.tulisanHitam));
        }
    }
}