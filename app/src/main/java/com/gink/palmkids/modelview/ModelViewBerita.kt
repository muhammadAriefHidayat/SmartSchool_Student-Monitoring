package com.gink.palmkids.modelview

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import com.gink.palmkids.Url
import com.gink.palmkids.model.HomeBeritaModel
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class ModelViewBerita:ViewModel() {
    private val listViewBerita = MutableLiveData<ArrayList<HomeBeritaModel>>()

    internal fun dataBerita(token: String,context: Context) {
        val client = AsyncHttpClient()
        val url = "$Url/api/main/announcement"
        val dataBeritaModel = ArrayList<HomeBeritaModel>()
        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization","Bearer $token")

        client.get(url, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String) {
                try {
                    val responseArray = JSONArray(responseString)
                    for (i in 0 until responseArray.length()){
                        val responseObject = responseArray.getJSONObject(i)
                        val item_Berita  = HomeBeritaModel()
                        item_Berita.id = responseObject.getString("id")
                        item_Berita.title = responseObject.getString("title")
                        item_Berita.text = responseObject.getString("text")
                        item_Berita.class_id = responseObject.getString("class_id")
                        item_Berita.created_by = responseObject.getString("created_by")
                        item_Berita.created = responseObject.getString("created")
                        Log.d("inimau", responseObject.toString())
                        dataBeritaModel.add(item_Berita)
                    }
                    listViewBerita.postValue(dataBeritaModel)
                }catch (e: Exception){
                    Log.d("inimaus", e.message.toString())
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                Log.d("inimaue", statusCode.toString())
            }
        })
    }

    internal fun getDataBerita(): LiveData<ArrayList<HomeBeritaModel>> {
        return listViewBerita
    }
}