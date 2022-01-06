package com.gink.palmkids.activity


import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gink.palmkids.AppPref
import com.google.android.material.navigation.NavigationView
import com.gink.palmkids.Extra
import com.gink.palmkids.Newextra
import com.gink.palmkids.R
import com.gink.palmkids.adapter.AdapterHomeBerita
import com.gink.palmkids.model.Profil
import com.gink.palmkids.modelview.ModelViewBerita
import com.gink.palmkids.utils.Utils
import com.gink.palmkids.utils.Utils.loading
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.menu_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var adapter: AdapterHomeBerita
    private lateinit var dataBerita: ModelViewBerita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_home)
        initUI()

        val profil_item = intent.getParcelableExtra<Profil>(Extra)
        val token = intent.getStringExtra(Newextra)

        FirebaseMessaging.getInstance().subscribeToTopic("ayee")

        FirebaseMessaging.getInstance().subscribeToTopic(profil_item!!.id)

        home_name_txt.text = profil_item.username

        adapter = AdapterHomeBerita {
            val intent = Intent(applicationContext, DetailBeritaActivity::class.java)
            intent.putExtra(Extra, it)
            startActivity(intent)
        }

        adapter.notifyDataSetChanged()
        dataBerita = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelViewBerita::class.java
        )

        dataBerita.dataBerita(token!!, applicationContext)
        dataBerita.getDataBerita().observe(this, Observer {
            adapter.setDataBerita(it)
            loading(progresbar_homeberita, false)
        })

        val linearLayout = LinearLayoutManager(this)
        linearLayout.reverseLayout = true
        linearLayout.stackFromEnd = true
        home_recycleview.layoutManager = linearLayout
        home_recycleview.setHasFixedSize(true);
        home_recycleview.adapter = adapter

        val navigation = findViewById<NavigationView>(R.id.nav_view)
        val header = navigation.getHeaderView(0)
        val userHeader = header.findViewById<TextView>(R.id.menu_username)
        val headerImae = header.findViewById<ImageView>(R.id.userImageView)
        val classheader = header.findViewById<TextView>(R.id.menu_kelas)
        classheader.text = profil_item.class_name
        userHeader.text = profil_item.username

        when {
            profil_item.avatar.count() <= 4 -> {
                headerImae.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.kind))
            }
            profil_item.avatar.isNullOrEmpty() -> {
                headerImae.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.kind))
            }
            else -> {
                val profil_image = profil_item.avatar
                Glide.with(this).load(profil_image).into(headerImae)
            }
        }

        nav_view.setNavigationItemSelectedListener(this)
        if (Utils.isRTL) {
            nav_view.textDirection = View.TEXT_DIRECTION_RTL
        } else {
            nav_view.textDirection = View.TEXT_DIRECTION_LTR
        }
    }

    private fun initUI() {
        initToolbar()
        val toggle = ActionBarDrawerToggle(
            this,
            home_drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        home_drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        if (Utils.isRTL) {
            navigationView.textDirection = View.TEXT_DIRECTION_RTL
        } else {
            navigationView.textDirection = View.TEXT_DIRECTION_LTR
        }
    }


    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24)
        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.md_white_1000
                ), PorterDuff.Mode.SRC_ATOP
            )
        }

        toolbar.title = "Palm Kids"

        try {
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.md_white_1000))
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
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val token = intent.getStringExtra(Newextra)
        val profil_item = intent.getParcelableExtra<Profil>(Extra)

        if (id == R.id.nav_profil) {
            val intent = Intent(this, ProfilActivity::class.java)
            intent.putExtra(Extra, profil_item)
            startActivity(intent)

        } else if (id == R.id.nav_subject) {
            val intent = Intent(this, SubjectActivity::class.java)
            intent.putExtra(Extra, token)
            startActivity(intent)

        } else if (id == R.id.nav_attendance) {
            val intent = Intent(this, AttendanceActivity::class.java)
            intent.putExtra(Extra, token)
            intent.putExtra(Newextra, profil_item!!.username)
            startActivity(intent)

        } else if (id == R.id.nav_tuition) {
            val intent = Intent(this, TuitionActivity::class.java)
            intent.putExtra(Extra, token)
            startActivity(intent)

        } else if (id == R.id.tutitonstudent) {
            val intent = Intent(this, TuitionStudentActivity::class.java)
            intent.putExtra(Extra, token)
            startActivity(intent)

        }else if (id == R.id.nav_question) {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra(Extra, token)
            startActivity(intent)

        } else if (id == R.id.nav_private) {
            val intent = Intent(this, PrivateCombookActivity::class.java)
            intent.putExtra(Extra, token)
            startActivity(intent)

        } else if (id == R.id.nav_logout) {
            AppPref.isLogin = false
            AppPref.username = ""
            AppPref.password = ""
            AppPref.token = ""
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        val drawer = findViewById<DrawerLayout>(R.id.home_drawer)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}