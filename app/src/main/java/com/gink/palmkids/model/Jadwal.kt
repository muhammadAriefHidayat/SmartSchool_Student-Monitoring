package com.gink.palmkids.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Jadwal(var jam:String?, var mapel: String?): Parcelable {
    constructor() : this(",","")
}