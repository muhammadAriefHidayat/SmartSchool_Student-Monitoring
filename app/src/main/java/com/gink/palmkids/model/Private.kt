package com.gink.palmkids.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Private(
    @field:SerializedName("id")
    var id:String,
    @field:SerializedName("user_id")
    var user_id:String,
    @field:SerializedName("class_id")
    var class_id:String,
    @field:SerializedName("type")
    var type:String,
    @field:SerializedName("text")
    var text:String,
    @field:SerializedName("created")
    var created:String
):Parcelable {
    constructor():this("","","","","","")
}