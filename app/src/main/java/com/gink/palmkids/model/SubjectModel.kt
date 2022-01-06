package com.gink.palmkids.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SubjectModel(val jam:String?, val judul: String?):Parcelable {
    constructor() : this(",","")
}