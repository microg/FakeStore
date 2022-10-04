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

package com.google.android.play.core.splitinstall.protocol;

import android.os.Bundle;

interface ISplitInstallServiceCallback {

    void onStartInstall(int i, in Bundle bundle);

    // Method not identified yet
    void b();

    void onCompleteInstall(int i);

    void onCancelInstall(int i, in Bundle bundle);

    void onGetSession(int i, in Bundle bundle);

    void onError(in Bundle bundle);

    void onGetSessionStates(in List<Bundle> list);

    void onDeferredUninstall(in Bundle bundle);

    void onDeferredInstall(in Bundle bundle);

    void onGetSplitsForAppUpdate();

    void onCompleteInstallForAppUpdate();

    void onDeferredLanguageInstall();
}