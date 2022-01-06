package com.gink.palmkids.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profil(
    @field:SerializedName("id")
    var id: String,
    @field:SerializedName("username")
    var username: String,
    @field:SerializedName("email")
    var email: String,
    @field:SerializedName("first_name")
    var first_name: String,
    @field:SerializedName("last_name")
    var last_name: String,
    @field:SerializedName("phone")
    var phone: String,
    @field:SerializedName("avatar")
    var avatar:String,
    @field:SerializedName("ttl")
    var ttl:String,
    @field:SerializedName("address")
    var address: String,
    @field:SerializedName("class_name")
    var class_name:String,
    @field:SerializedName("gender")
    var gender :String,
    @field:SerializedName("father_name")
    var father_name: String,
    @field:SerializedName("father_email")
    var father_email: String,
    @field:SerializedName("father_address")
    var father_address :String,
    @field:SerializedName("father_phone")
    var father_phone: String,
    @field:SerializedName("mother_name")
    var mother_name :String,
    @field:SerializedName("mother_email")
    var mother_email: String,
    @field:SerializedName("mother_address")
    var mother_address :String,
    @field:SerializedName("mother_phone")
    var mother_phone :String,
):Parcelable{
    constructor():this("","","","","",
        "","","","","","","",
    "","","","","","","")
}