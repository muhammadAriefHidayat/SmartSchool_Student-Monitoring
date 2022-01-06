package com.gink.palmkids.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Token(var token:String) : Parcelable {
    constructor():this("")
}