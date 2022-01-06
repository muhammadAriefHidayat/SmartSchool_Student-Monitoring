package com.gink.palmkids.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReplyPrivate(
    @field:SerializedName("compbook_id")
    var compbook_id:String,
    @field:SerializedName("user_id")
    var user_id:String,
    @field:SerializedName("text")
    var text:String,
    @field:SerializedName("created")
    var created:String,
    @field:SerializedName("align")
    var align:String,
    @field:SerializedName("first_name")
    var first_name:String

):Parcelable{
    constructor():this("","","","","","")
}