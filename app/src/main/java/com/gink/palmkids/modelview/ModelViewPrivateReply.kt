package com.gink.palmkids.modelview

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gink.palmkids.Url
import com.gink.palmkids.model.Reply
import com.gink.palmkids.utils.Utils
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import java.lang.Exception

class ModelViewPrivateReply:ViewModel(){
    private val listViewReply = MutableLiveData<ArrayList<Reply>>()

    internal fun dataReply(token: String, id_topik:String, text:String,context: Context) {
        val client = AsyncHttpClient()
        val url = "$Url/api/main/compbookreply"

        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization","Bearer $token")

        val params = RequestParams()
        params.put("compbook_id", id_topik);
        params.put("text", text);
        client.post(url,params, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String) {
                try {
                    val result = responseString
                    Utils.peringatan(context, result)
                }catch (e: Exception){
                    Utils.peringatan(context, e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                Utils.peringatan(context, responseString.toString())
            }
        })
    }

    internal fun getDataReply(): LiveData<ArrayList<Reply>> {
        return listViewReply
    }
}