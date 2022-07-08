package com.android.vending.licensing

import android.app.Service
import android.content.Intent
import android.os.IBinder

class LicensingService : Service() {

    companion object {
        const val LICENSED_OLD_KEY = 2
    }

    private val mServiceInterface = object : ILicensingService.Stub() {
        override fun checkLicense(
            someLong: Long,
            someString: String,
            listener: ILicenseResultListener
        ) {
            // Answer allow for each request
            listener.allow(LICENSED_OLD_KEY)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return mServiceInterface
    }
}