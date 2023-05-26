/*
 * SPDX-FileCopyrightText: 2023 microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 */

package com.google.android.play.core.integrityservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class IntegrityService extends Service {

    private final IIntegrityService.Stub mIntegrityService = new IIntegrityService.Stub() {

    };

    public IBinder onBind(Intent intent) {
        return mIntegrityService;
    }
}