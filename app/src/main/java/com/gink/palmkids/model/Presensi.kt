package com.gink.palmkids.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Presensi(var date:String?): Parcelable {
    constructor() : this("")
}