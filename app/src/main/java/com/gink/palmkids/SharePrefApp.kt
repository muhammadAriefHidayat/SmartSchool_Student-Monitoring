package com.gink.palmkids

import android.app.Application


class SharePrefApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPref.init(this)
    }
}