package com.gink.palmkids.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Forum(
    @field:SerializedName("id")
    var id:String,
    @field:SerializedName("title")
    var title:String,
    @field:SerializedName("description")
    var description:String,
    @field:SerializedName("created")
    var created:String,
):Parcelable {
    constructor():this("","","","")
}