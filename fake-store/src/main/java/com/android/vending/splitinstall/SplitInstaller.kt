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

package com.android.vending.splitinstall

import android.content.Context
import com.android.vending.splitinstall.installer.DummyStoreSplitInstaller

interface SplitInstaller {
    fun initialize()
    fun install(packageName: String, moduleName: String)
    fun destroy()
}

enum class SplitInstallerType { DummyStoreSplitInstaller }

object SplitInstallerFactory {
    fun createSplitInstaller(context: Context, type: SplitInstallerType): SplitInstaller {
        when (type) {
            SplitInstallerType.DummyStoreSplitInstaller ->
                return DummyStoreSplitInstaller(context)
            else -> throw IllegalArgumentException("Unknown SplitInstallerType")
        }
    }
}
