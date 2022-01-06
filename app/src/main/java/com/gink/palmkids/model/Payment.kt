package com.gink.palmkids.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Payment(var id:String?, var text: String?, var jumlah_bayar: String?, var date: String?): Parcelable {
    constructor() : this(",","","","")
}