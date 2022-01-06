package com.gink.palmkids.activity

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.gink.palmkids.Extra
import com.gink.palmkids.Newextra
import com.gink.palmkids.R
import com.gink.palmkids.modelview.ModelViewPresensi
import kotlinx.android.synthetic.main.activity_attendance.*
import java.text.ParseException
import java.text.SimpleDateFormat


class AttendanceActivity : AppCompatActivity() {
    lateinit var inActiveDate: String
    lateinit var modelViewPresensi :ModelViewPresensi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)
        initToolbar()
        val token = intent.getStringExtra(Extra)
        val username = intent.getStringExtra(Newextra)
        val calendarView = findViewById<View>(R.id.add_calendar) as CalendarView

        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                modelViewPresensi = ModelViewPresensi()
                for (calendar in calendarView.selectedDates) {
                    val date = calendar.time
                    val format1 = SimpleDateFormat("yyyy-MM-dd")
                    inActiveDate = format1.format(date)
                    val textCalend = findViewById<TextView>(R.id.presensi_calend)
                    modelViewPresensi.dataPresensi(token!!,inActiveDate,username!!,textCalend,this@AttendanceActivity)
                }
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
        toolbar_tanggal.setNavigationIcon(R.drawable.baseline_arrow_back_black_24)

        if (toolbar_tanggal.navigationIcon != null) {
            toolbar_tanggal.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }
        try {
            toolbar_tanggal.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        toolbar_tanggal.title = ""

        try {
            setSupportActionBar(toolbar_tanggal)
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
        toolbar_tanggal.setOnClickListener {
            finish()
        }
    }
}