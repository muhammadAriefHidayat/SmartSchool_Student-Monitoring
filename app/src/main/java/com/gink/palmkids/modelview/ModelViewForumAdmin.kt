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
import com.gink.palmkids.model.Forum
import com.gink.palmkids.utils.Utils
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class ModelViewForumAdmin :ViewModel() {
    private val listViewForum = MutableLiveData<ArrayList<Forum>>()

    fun setForumAdmin(token:String, context: Context,login_progressbar: ProgressBar){
        val client = AsyncHttpClient()
        val url = "$Url/api/main/forum/"
        val dataForum  = ArrayList<Forum>()
        client.addHeader("Accept", "application/json")
        client.addHeader("Authorization","Bearer $token")

        client.get(url, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>,
                                   responseString: String) {
                try {
                    val responseArray = JSONArray(responseString)
                    for (i in 0 until responseArray.length()){
                        val item_forum = Forum()
                        val resobject = responseArray.getJSONObject(i)
                        item_forum.id = resobject.getString("id")
                        item_forum.title = resobject.getString("title")
                        item_forum.description = resobject.getString("description")
                        item_forum.created = resobject.getString("created")
                        dataForum.add(item_forum)
                    }
                    Log.d("cobabs", responseString)
                    listViewForum.postValue(dataForum)
                    login_progressbar.visibility = View.INVISIBLE
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

    internal fun getDataForumAdmin():LiveData<ArrayList<Forum>>{
        return listViewForum
    }
}