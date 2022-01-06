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
import com.gink.palmkids.adapter.AdapterSubject
import com.gink.palmkids.fragment.*
import kotlinx.android.synthetic.main.activity_subject.*

class SubjectActivity : AppCompatActivity() {
    private lateinit var adapter: AdapterSubject
    val position = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)

        initToolbar()

        val token = intent.getStringExtra(Extra)
        mond(true, token!!)
        tue(false, token)
        wed(false, token)
        thus(false, token)
        fri(false, token)
        sat(false, token)

        subject_monday.setOnClickListener {
            mond(true,token)
            tue(false, token)
            wed(false, token)
            thus(false, token)
            fri(false, token)
            sat(false, token)
        }

        subject_tues.setOnClickListener {
            mond(false, token)
            tue(true, token)
            wed(false, token)
            thus(false, token)
            fri(false, token)
            sat(false, token)
        }

        subject_wed.setOnClickListener {
            mond(false, token)
            tue(false,token)
            wed(true, token)
            thus(false, token)
            fri(false, token)
            sat(false, token)
        }

        subject_thu.setOnClickListener {
            mond(false, token)
            tue(false, token)
            wed(false, token)
            thus(true, token)
            fri(false, token)
            sat(false, token)
        }

        subject_fri.setOnClickListener {
            mond(false, token)
            tue(false, token)
            wed(false, token)
            thus(false, token)
            fri(true, token)
            sat(false, token)
        }

        subject_sat.setOnClickListener {
            mond(false, token)
            tue(false, token)
            wed(false, token)
            thus(false, token)
            fri(false, token)
            sat(true, token)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {
        toolbar_subject.setNavigationIcon(R.drawable.baseline_arrow_back_black_24)

        if (toolbar_subject.navigationIcon != null) {
            toolbar_subject.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar_subject.title = "Jadwal Pelajaran"

        try {
            toolbar_subject.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar_subject)
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
        toolbar_subject.setOnClickListener {
            finish()
        }
    }

    private fun setupFragment(fragment: Fragment) {
        try {
            this.supportFragmentManager.beginTransaction()
                .replace(R.id.frame_subject, fragment)
                .commitAllowingStateLoss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun mond(clicked: Boolean, token: String){
        if (clicked){
            subject_monday.setBackgroundResource(R.drawable.bakcgroud_rounded_pink)
            subject_monday.setTextColor(ContextCompat.getColor(applicationContext, R.color.md_white_1000));
            setupFragment(MondayFragment.newHomeInstance(token))
        }else{
            subject_monday.setTextColor(ContextCompat.getColor(applicationContext, R.color.tulisanHitam));
            subject_monday.setBackgroundResource(R.color.md_white_1000)
            subject_monday.text = "Mon"
        }
    }

    fun tue(clicked: Boolean, token: String){
        if (clicked){
            setupFragment(TueFragment.newHomeInstance(token))
            subject_tues.setTextColor(ContextCompat.getColor(applicationContext, R.color.md_white_1000));
            subject_tues.setBackgroundResource(R.drawable.bakcgroud_rounded_pink)
        }else{
            subject_tues.setTextColor(ContextCompat.getColor(applicationContext, R.color.tulisanHitam));
            subject_tues.setBackgroundResource(R.color.md_white_1000)
            subject_tues.text = "Tue"
        }
    }

    fun wed(clicked: Boolean, token: String){
        if (clicked){
            setupFragment(WedFragment.newHomeInstance(token))
            subject_wed.setTextColor(ContextCompat.getColor(applicationContext, R.color.md_white_1000));
            subject_wed.setBackgroundResource(R.drawable.bakcgroud_rounded_pink)
        }else{
            subject_wed.setTextColor(ContextCompat.getColor(applicationContext, R.color.tulisanHitam));
            subject_wed.setBackgroundResource(R.color.md_white_1000)
            subject_wed.text = "Wed"
        }
    }

    fun thus(clicked: Boolean, token: String){
        if (clicked){
            setupFragment(ThuFragment.newHomeInstance(token))
            subject_thu.setTextColor(ContextCompat.getColor(applicationContext, R.color.md_white_1000));
            subject_thu.setBackgroundResource(R.drawable.bakcgroud_rounded_pink)
        }else{
            subject_thu.setTextColor(ContextCompat.getColor(applicationContext, R.color.tulisanHitam));
            subject_thu.setBackgroundResource(R.color.md_white_1000)
            subject_thu.text = "Thu"
        }
    }

    fun fri(clicked: Boolean, token: String){
        if (clicked){
            setupFragment(FriFragment.newHomeInstance(token))
            subject_fri.setTextColor(ContextCompat.getColor(applicationContext, R.color.md_white_1000));
            subject_fri.setBackgroundResource(R.drawable.bakcgroud_rounded_pink)
        }else{
            subject_fri.setBackgroundResource(R.color.md_white_1000)
            subject_fri.setTextColor(ContextCompat.getColor(applicationContext, R.color.tulisanHitam));
            subject_fri.text = "Fri"
        }
    }

    fun sat(clicked: Boolean, token: String){
        if (clicked){
            setupFragment(SatFragment.newHomeInstance(token))
            subject_sat.setTextColor(ContextCompat.getColor(applicationContext, R.color.md_white_1000));
            subject_sat.setBackgroundResource(R.drawable.bakcgroud_rounded_pink)
        }else{
            subject_sat.setBackgroundResource(R.color.md_white_1000)
            subject_sat.setTextColor(ContextCompat.getColor(applicationContext, R.color.tulisanHitam));
            subject_sat.text = "Sat"
        }
    }
}
