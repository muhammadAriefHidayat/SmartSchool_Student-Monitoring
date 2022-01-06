package com.gink.palmkids.modelview

import android.content.Context
import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gink.palmkids.AppPref
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import com.gink.palmkids.Url
import com.gink.palmkids.model.Token
import com.gink.palmkids.utils.Utils
import com.gink.palmkids.utils.Utils.peringatan
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class ModelviewToken :ViewModel(){
    private val listToken = MutableLiveData<ArrayList<Token>>()

    fun setCurrentLogin(username: String,password: String,context: Context,login_progressbar: ProgressBar){
        val client = AsyncHttpClient()
        val url = "$Url/api/auth/login"
        val dataToken  = ArrayList<Token>()
        val params = RequestParams()
        params.put("username", username);
        params.put("password", password);

        client.post(url, params, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>,
                responseString: String) {
                try {
                    AppPref.isLogin = true
                    AppPref.username = username
                    AppPref.password = password

                    val responseObject = JSONObject(responseString)
                    val id_token = Token()
                    id_token.token = responseObject.getString("token")
                    Log.d("yangini", id_token.token.toString())
                    dataToken.add(id_token)
                    listToken.postValue(dataToken)
                    peringatan(context,"Login Berhasil")
                } catch (e: Exception) {
                    peringatan(context,e.message.toString())
                    Utils.loading(login_progressbar, false)
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                peringatan(context,"Username Atau Password Salah")
                Utils.loading(login_progressbar, false)
            }
        })
    }

    internal fun getToken(): LiveData<ArrayList<Token>>{
        return listToken
    }
}