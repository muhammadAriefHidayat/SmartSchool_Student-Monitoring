package com.gink.palmkids.modelview

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import com.gink.palmkids.Url
import com.gink.palmkids.activity.AttendanceActivity
import com.gink.palmkids.model.Presensi

import com.gink.palmkids.utils.Utils
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class ModelViewPresensi() : ViewModel() {
    private val listViewReply = MutableLiveData<ArrayList<Presensi>>()

    internal fun dataPresensi(
        token: String,
        date: String,
        username: String,
        textCalend: TextView,
        context: AttendanceActivity
    ) {
        val client = AsyncHttpClient()
        val url = "$Url/api/main/presensi"
        val dataPresensi = ArrayList<Presensi>()
        client.addHeader("Accept", "application/json");
        client.addHeader("Authorization", "Bearer $token")

        val params = RequestParams()
        Log.d("result", date)
        params.put("date", date);

        client.post(url, params, object : TextHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String
            ) {
                try {
                    val result = JSONObject(responseString)
                    val tanggal = result.getString("date")
                    Log.d("result", result.toString())
                    Log.d("result", tanggal.toString())
                    if (result == null) {
                        textCalend.text = "tanggal $date $username TIDAK mengikuti pelajaran"
                    } else {
                        textCalend.text = "tanggal $date $username hadir dan mengikuti pelajaran"
                    }
                } catch (e: Exception) {
                    textCalend.text = "tanggal $date $username TIDAK mengikuti pelajaran"
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                textCalend.text = "tanggal $date $username TIDAK mengikuti pelajaran"
            }
        })
    }

    internal fun getDataReply(): LiveData<ArrayList<Presensi>> {
        return listViewReply
    }
}