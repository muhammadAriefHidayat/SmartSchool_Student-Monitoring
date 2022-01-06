package com.gink.palmkids.modelview

import android.content.Context
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gink.palmkids.AppPref
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import com.gink.palmkids.Url
import com.gink.palmkids.model.Profil
import com.gink.palmkids.utils.Utils
import com.gink.palmkids.utils.Utils.peringatan
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_splashsreen.*
import org.json.JSONObject
import java.lang.Exception

class ModelViewProfil:ViewModel() {
    private val listViewProfil = MutableLiveData<ArrayList<Profil>>()

    internal fun dataLogin(token: String, context: Context, splash_progressbar: ProgressBar) {
        val client = AsyncHttpClient()
        val url = "$Url/api/main/home"
        val dataProfil = ArrayList<Profil>()
        client.addHeader("Accept", "application/json")
        client.addHeader("Authorization","Bearer $token")

        client.get(url, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String) {
                try {
                    val result = responseString
                    val responseObject = JSONObject(result)
                    val item_Profil  = Profil()

                    item_Profil.id = responseObject.getString("id")
                    item_Profil.username = responseObject.getString("username")
                    item_Profil.email = responseObject.getString("email")
                    item_Profil.first_name = responseObject.getString("first_name")
                    item_Profil.last_name = responseObject.getString("last_name")
                    item_Profil.ttl = responseObject.getString("ttl")
                    item_Profil.class_name = responseObject.getString("class_name")

                    item_Profil.avatar = responseObject.getString("avatar")
                    item_Profil.phone = responseObject.getString("phone")
                    item_Profil.address = responseObject.getString("address")
                    item_Profil.gender = responseObject.getString("gender")

                    if (responseObject.getString("father_name").isNotEmpty()){
                        item_Profil.father_name = responseObject.getString("father_name")
                        item_Profil.father_email = responseObject.getString("father_email")
                        item_Profil.father_address = responseObject.getString("father_address")
                        item_Profil.father_phone = responseObject.getString("father_phone")
                        item_Profil.mother_name = responseObject.getString("mother_name")
                        item_Profil.mother_email = responseObject.getString("mother_email")
                        item_Profil.mother_address = responseObject.getString("mother_address")
                        item_Profil.mother_phone = responseObject.getString("mother_phone")
                    }

                    dataProfil.add(item_Profil)
                    listViewProfil.postValue(dataProfil)
                }catch (e: Exception){
                    AppPref.isLogin = false
                    AppPref.username = ""
                    AppPref.password = ""
                    peringatan(context,e.message.toString())
                    Utils.loading(splash_progressbar, true)
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                AppPref.isLogin = false
                AppPref.username = ""
                AppPref.password = ""
                peringatan(context, responseString.toString())
                Utils.loading(splash_progressbar, true)
            }
        })
    }

    internal fun getDataProfil():LiveData<ArrayList<Profil>>{
        return listViewProfil
    }
}