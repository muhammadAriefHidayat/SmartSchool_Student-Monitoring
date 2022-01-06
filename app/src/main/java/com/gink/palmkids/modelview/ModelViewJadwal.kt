package com.gink.palmkids.modelview

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import com.gink.palmkids.Url
import com.gink.palmkids.model.Jadwal
import com.gink.palmkids.utils.Utils
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class ModelViewJadwal:ViewModel() {
    private val listViewJadwal = MutableLiveData<ArrayList<Jadwal>>()

    internal fun dataJadwal(token: String,context: Context,hari:String) {
        val client = AsyncHttpClient()
        val url = "$Url/api/main/schedule"
        val dataProfil = ArrayList<Jadwal>()
        client.addHeader("Accept", "application/json")
        client.addHeader("Authorization","Bearer $token")

        client.get(url, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String) {
                try {
                    val responArray = JSONObject(responseString)
                    val monday = responArray.getJSONArray(hari)
                    for (i in 0 until  monday.length()){
                        val responsObjects = monday.getJSONObject(i)
                        val itemJadwal = Jadwal()
                        itemJadwal.jam = responsObjects.getString("0")
                        itemJadwal.mapel = responsObjects.getString("1")
                        dataProfil.add(itemJadwal)
                    }
                    listViewJadwal.postValue(dataProfil)
                }catch (e: Exception){
                    Utils.peringatan(context, e.message.toString())
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                Utils.peringatan(context, responseString.toString())
            }
        })
    }

    internal fun getDataJadwal():LiveData<ArrayList<Jadwal>>{
        return listViewJadwal
    }
}