package com.gink.palmkids.modelview

import android.content.Context
import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import com.gink.palmkids.Url
import com.gink.palmkids.model.Forum
import com.gink.palmkids.utils.Utils
import cz.msebera.android.httpclient.Header
import java.lang.Exception

class ModelViewForum:ViewModel() {
    private val listViewForum = MutableLiveData<ArrayList<Forum>>()

    fun setForum(title: String,desc: String,token:String, context: Context,login_progressbar: ProgressBar){
        val client = AsyncHttpClient()
        val url = "$Url/api/main/forum/"
        val dataToken  = ArrayList<Forum>()
        client.addHeader("Accept", "application/json")
        client.addHeader("Authorization","Bearer $token")
        val params = RequestParams()
        params.put("title", title);
        params.put("description", desc);

        client.post(url, params, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>,
                                   responseString: String) {
                try {
                    val result = responseString
                    Log.d("cobab",result)

                } catch (e: Exception) {
                    Utils.peringatan(context, e.message.toString())
                    Log.d("cobabee", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                Log.d("cobabff",statusCode.toString())
                Utils.loading(login_progressbar, false)
            }
        })
    }

    internal fun getDataForum():LiveData<ArrayList<Forum>>{
        return listViewForum
    }
}