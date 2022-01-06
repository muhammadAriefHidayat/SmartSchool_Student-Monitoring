package com.gink.palmkids.modelview

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import com.gink.palmkids.Url
import com.gink.palmkids.model.Payment
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class ModelViewPayment:ViewModel() {
    private val listViewPayment = MutableLiveData<ArrayList<Payment>>()

    internal fun dataPayment(token: String,context: Context,progressBar: ProgressBar) {
        val client = AsyncHttpClient()
        val url = "$Url/api/main/payment"
        val dataPayment = ArrayList<Payment>()
        client.addHeader("Accept", "application/json")
        client.addHeader("Authorization","Bearer $token")

        client.get(url, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String) {
                try {
                    val responseArray = JSONArray(responseString)
                    for (i in 0 until responseArray.length()){
                        val responseObject = responseArray.getJSONObject(i)
                        val item_Berita  = Payment()
                        item_Berita.id = responseObject.getString("id")
                        item_Berita.text = responseObject.getString("text")
                        item_Berita.jumlah_bayar = responseObject.getString("jumlah_bayar")
                        item_Berita.date = responseObject.getString("date")

                        dataPayment.add(item_Berita)
                    }

                    listViewPayment.postValue(dataPayment)
                    progressBar.visibility = View.INVISIBLE
                }catch (e: Exception){
                    Log.d("inimaus", e.message.toString())
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                Log.d("inimaue", statusCode.toString())
            }
        })
    }
    internal fun getDataPayment():LiveData<ArrayList<Payment>>{
        return listViewPayment
    }
}