package com.android.vending.licensing;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LicensingService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}