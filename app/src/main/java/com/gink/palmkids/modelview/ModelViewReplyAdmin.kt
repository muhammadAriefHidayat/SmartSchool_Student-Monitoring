package com.gink.palmkids.modelview

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import com.gink.palmkids.Url

import com.gink.palmkids.model.Reply
import com.gink.palmkids.utils.Utils
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class ModelViewReplyAdmin():ViewModel() {
    private val listViewReply = MutableLiveData<ArrayList<Reply>>()

    internal fun setDataReply(token: String,id_topik:String,context: Context) {
        val client = AsyncHttpClient()
        val url = "$Url/api/main/reply/$id_topik"
        val dataProfil = ArrayList<Reply>()
        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization","Bearer $token")

        client.get(url, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String) {
                try {
                    val responseArray = JSONArray(responseString)
                    for (i in 0 until responseArray.length()){
                        val resobject = responseArray.getJSONObject(i)
                        val item_Reply  = Reply()
                        item_Reply.topic_id = resobject.getString("topic_id")
                        item_Reply.text = resobject.getString("text")
                        item_Reply.created = resobject.getString("created")
                        item_Reply.first_name = resobject.getString("first_name")
                        dataProfil.add(item_Reply)
                        Log.d("massa",item_Reply.toString())
                    }
                    listViewReply.postValue(dataProfil)
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
