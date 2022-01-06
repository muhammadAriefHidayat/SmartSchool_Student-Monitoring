package com.gink.palmkids.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class HomeBeritaModel(var id:String,var title:String, var text: String, var class_id:String,var created_by:String, var created:String):Parcelable {
    constructor() : this("","","","","","")
}