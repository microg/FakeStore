package com.android.vending.billing;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;

/**
 * Created by andrew.zhao on 9/19/16.
 */
public class InAppBillingService extends Service {

    private final IInAppBillingService.Stub mInAppBillingService = new IInAppBillingService.Stub() {
        @Override
        public int isBillingSupported(int apiVersion, String packageName, String type) throws RemoteException {
            Log.e("fakestore", "issupported");
            return 0;
        }

        @Override
        public Bundle getSkuDetails(int apiVersion, String packageName, String type, Bundle skusBundle) throws RemoteException {
            Log.e("fakestore", "getsku");
            return null;
        }

        @Override
        public Bundle getBuyIntent(int apiVersion, String packageName, String sku, String type, String developerPayload) throws RemoteException {
            Log.e("fakestore", "getbuy");
            return null;
        }

        @Override
        public Bundle getPurchases(int apiVersion, String packageName, String type, String continuationToken) throws RemoteException {
            Log.e("fakestore", "getpurchase");
            return null;
        }

        @Override
        public int consumePurchase(int apiVersion, String packageName, String purchaseToken) throws RemoteException {
            Log.e("fakestore", "consumepurchase");
            return 0;
        }

        @Override
        public int stub(int apiVersion, String packageName, String type) throws RemoteException {
            Log.e("fakestore", "stub");
            return 0;
        }

        @Override
        public Bundle getBuyIntentToReplaceSkus(int apiVersion, String packageName, List<String> oldSkus, String newSku, String type, String developerPayload) throws RemoteException {
            Log.e("fakestore", "getbuyintenttoreplace");
            return null;
        }
    };

    public IBinder onBind(Intent intent) {
        return mInAppBillingService;
    }
}
