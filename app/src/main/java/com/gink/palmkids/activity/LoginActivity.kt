package com.gink.palmkids.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gink.palmkids.*
import com.gink.palmkids.modelview.ModelViewProfil
import com.gink.palmkids.modelview.ModelviewToken
import com.gink.palmkids.utils.Utils.loading
import com.gink.palmkids.utils.Utils.peringatan
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var dataToken: ModelviewToken
    private lateinit var dataProfil: ModelViewProfil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_signin_btn.setOnClickListener {
            val username = login_username_txt.text.toString().trim()
            val password = login_password_txt.text.toString().trim()
            when {
                username.isEmpty() -> {
                    peringatan(this, "Username Tidak Boleh Kosong")
                    AppPref.isLogin = false
                    AppPref.username = ""
                    AppPref.password = ""
                    AppPref.token = ""
                }
                password.isEmpty() -> {
                    peringatan(this, "Password Tidak Boleh Kosong")
                    AppPref.isLogin = false
                    AppPref.username = ""
                    AppPref.password = ""
                    AppPref.token = ""
                }
                else -> {
                    logoin(username, password)
//                    AppPref.isLogin = true
//                    AppPref.username = username
//                    AppPref.password = password
                }
            }
        }
    }

    fun logoin(username: String, password: String) {
        loading(login_progressbar, true)
        dataToken = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelviewToken::class.java
        )
        dataToken.setCurrentLogin(username, password, this, login_progressbar)
        dataToken.getToken().observe(this, Observer { token ->
            if (token != null) {
                val token = token[0].token
                AppPref.token = token

                dataProfil =
                    ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                        ModelViewProfil::class.java
                    )
                dataProfil.dataLogin(token, applicationContext, login_progressbar)
                dataProfil.getDataProfil().observe(this, { profil ->
                    if (profil != null) {
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra(Extra, profil[0])
                        intent.putExtra(Newextra, token)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                })
            }
        })
    }

    fun login_forgot(view: View) {
        Toast.makeText(this, "Harap Hubungi Administrator Sekolah", Toast.LENGTH_LONG).show()
    }
}
