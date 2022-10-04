/*
 * Copyright 2013-2022 microG Project Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.vending

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.android.vending.splitinstall.SplitInstaller
import com.android.vending.splitinstall.SplitInstallerFactory
import com.android.vending.splitinstall.SplitInstallerType
import com.google.android.play.core.splitinstall.protocol.ISplitInstallService
import com.google.android.play.core.splitinstall.protocol.ISplitInstallServiceCallback

class SplitInstallService : Service() {

    companion object {
        const val TAG = "SplitInstallService"
    }

    private lateinit var mSplitInstaller: SplitInstaller

    override fun onCreate() {
        super.onCreate()

        mSplitInstaller = SplitInstallerFactory.createSplitInstaller(
            applicationContext,
            SplitInstallerType.DummyStoreSplitInstaller
        )

        mSplitInstaller.initialize()
    }

    override fun onBind(p0: Intent?): IBinder {
        return mServiceInterface
    }

    override fun onDestroy() {
        super.onDestroy()
        mSplitInstaller.destroy()
    }

    private var mServiceInterface = object : ISplitInstallService.Stub() {

        override fun a() {
            Log.d(TAG, "a")
        }

        override fun startInstall(
            packageName: String,
            list: List<Bundle>,
            bundle: Bundle,
            callback: ISplitInstallServiceCallback
        ) {
            for (element in list) {
                mSplitInstaller.install(packageName, element.get("module_name").toString())
            }
        }

        override fun c(str: String?) {
            Log.d(TAG, "c")
        }

        override fun d(str: String?) {
            Log.d(TAG, "d")
        }

        override fun e(str: String?) {
            Log.d(TAG, "e")
        }

        override fun getSessionStates(str: String, callback: ISplitInstallServiceCallback) {
            Log.d(TAG, "onGetSessionStates")
            callback.onGetSessionStates(arrayListOf<Bundle>())
        }

        override fun g(str: String?) {
            Log.d(TAG, "g")
        }

        override fun h(str: String?, callback: ISplitInstallServiceCallback?) {
            Log.d(TAG, "h $str $callback")
        }

        override fun i(str: String?) {
            Log.d(TAG, "i")
        }

        override fun j(str: String?) {
            Log.d(TAG, "j")
        }

        override fun k(str: String?) {
            Log.d(TAG, "k")
        }

        override fun l(str: String?) {
            Log.d(TAG, "l")
        }

        override fun m(str: String?) {
            Log.d(TAG, "m")
        }
    }
}
