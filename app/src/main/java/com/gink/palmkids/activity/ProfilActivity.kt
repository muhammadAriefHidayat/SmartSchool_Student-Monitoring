package com.gink.palmkids.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.gink.palmkids.Extra
import com.gink.palmkids.R
import com.gink.palmkids.model.Profil
import com.gink.palmkids.utils.Utils
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val profil_item = intent.getParcelableExtra<Profil>(Extra)
        profil_Nametxt.text = profil_item!!.username
        profil_addressValue.text = profil_item.address
        profil_emailValue.text = profil_item.email
        profil_genderValue.text = profil_item.gender
        profil_phoneValue.text = profil_item.phone
        profil_birthdateValue.text = profil_item.ttl

        profil_txt.text = "class: ${profil_item.class_name}"
        profil_gradeValue.text = profil_item.class_name

        profil_parentnameValue.text = profil_item.father_name
        profil_parentaddressValue.text = profil_item.father_address
        profil_parentemailValue.text = profil_item.father_email
        profil_parent_pohoneValue.text = profil_item.father_phone

        when {
            profil_item.avatar.count() <= 4 -> {
                profil_userimage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.kind))
            }
            profil_item.avatar.isNullOrEmpty() -> {
                profil_userimage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.kind))
            }
            else -> {
                Glide.with(this).load(profil_item.avatar).into(profil_userimage)
            }
        }
    }
}