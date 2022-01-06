package com.gink.palmkids.activity

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.gink.palmkids.AppPref
import com.gink.palmkids.Extra
import com.gink.palmkids.Newextra
import com.gink.palmkids.R
import com.gink.palmkids.modelview.ModelViewProfil
import com.gink.palmkids.modelview.ModelviewToken
import com.gink.palmkids.utils.Utils
import kotlinx.android.synthetic.main.activity_splashsreen.*

class SplashScreenActivity : AppCompatActivity() {
    private var isRunning: Boolean = true
    private lateinit var dataToken: ModelviewToken
    private lateinit var dataProfil: ModelViewProfil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashsreen)
    }

    fun login_btn(view: View) {
        logoin()
    }

    fun logoin() {
        if (AppPref.isLogin) {
            val buttonlogin = findViewById<Button>(R.id.exploreButton)
             Utils.loading(splash_progressbar, true)
            val token = AppPref.token
            Log.d("tokenpref",token)

            dataProfil =
                ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                    ModelViewProfil::class.java
                )
            dataProfil.dataLogin(token, applicationContext, splash_progressbar)
            dataProfil.getDataProfil().observe(this, { profil ->
                if (profil != null) {
                    Log.d("yangini", profil[0].toString())
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra(Extra, profil[0])
                    intent.putExtra(Newextra, token)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }else {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            })
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        Utils.setImageToImageView(this, s2bgImageView, R.drawable.pattern)

        if (!isRunning) {
            isRunning = false

            iconImageView.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(0)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator) {

                    }

                    override fun onAnimationEnd(animator: Animator) {

                        iconImageView.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(1500)
                            .setListener(object : Animator.AnimatorListener {
                                override fun onAnimationStart(animator: Animator) {

                                }

                                override fun onAnimationEnd(animator: Animator) {
                                    nameTextView.animate().alpha(1f).setDuration(800)
                                        .setListener(object : Animator.AnimatorListener {
                                            override fun onAnimationStart(animator: Animator) {

                                            }

                                            override fun onAnimationEnd(animator: Animator) {
                                                exploreButton.animate().alpha(1f).setDuration(400)
                                                    .start()
                                            }

                                            override fun onAnimationCancel(animator: Animator) {

                                            }

                                            override fun onAnimationRepeat(animator: Animator) {

                                            }
                                        }).start()

                                }

                                override fun onAnimationCancel(animator: Animator) {

                                }

                                override fun onAnimationRepeat(animator: Animator) {

                                }
                            }).start()

                    }

                    override fun onAnimationCancel(animator: Animator) {

                    }

                    override fun onAnimationRepeat(animator: Animator) {

                    }
                }).start()

        }
    }
}