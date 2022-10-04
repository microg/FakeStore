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

package com.android.vending.splitinstall.installer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.android.vending.splitinstall.SplitInstaller
import com.dummy.store.IDummyStoreSplitInstallService

class DummyStoreSplitInstaller(
    private val context: Context
) : SplitInstaller {

    companion object {
        private val ON_DEMAND_DELIVERY_SERVICE_COMPONENT =
            ComponentName("com.dummy.store", "com.dummy.store.splitinstall.SplitInstallService")
    }

    private var service: IDummyStoreSplitInstallService? = null
    private val moduleList = ArrayList<InstallModule>()

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, binder: IBinder) {
            service = IDummyStoreSplitInstallService.Stub.asInterface(binder)
            installWaitingModules()
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            service = null
        }
    }

    override fun initialize() {
        val intent = Intent().apply {
            component = ON_DEMAND_DELIVERY_SERVICE_COMPONENT
        }

        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun destroy() {
        context.unbindService(serviceConnection)
    }

    override fun install(packageName: String, moduleName: String) {
        if (service == null) {
            moduleList.add(InstallModule(packageName, moduleName))
        }

        service?.installSplitModule(packageName, moduleName)
    }

    private fun installWaitingModules() {
        val iterator = moduleList.iterator()
        while (iterator.hasNext()) {
            val module = iterator.next()
            service?.installSplitModule(module.packageName, module.moduleName)
            iterator.remove()
        }
    }

    private data class InstallModule(val packageName: String, val moduleName: String)
}
