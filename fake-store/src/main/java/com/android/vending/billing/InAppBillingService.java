package com.android.vending.billing;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew.zhao on 9/19/16.
 */
public class InAppBillingService extends Service {
    private static final String TAG = "FakeInAppStore";

    private final IInAppBillingService.Stub mInAppBillingService = new IInAppBillingService.Stub() {
        @Override
        public int isBillingSupported(int apiVersion, String packageName, String type) throws RemoteException {
            Log.d(TAG, "isBillingSupported(" + apiVersion + ", " + packageName + ", " + type + ")");
            return 0;
        }

        @Override
        public Bundle getSkuDetails(int apiVersion, String packageName, String type, Bundle skusBundle) throws RemoteException {
            Log.d(TAG, "getSkuDetails(" + apiVersion + ", " + packageName + ", " + type + ")");
            Bundle data = new Bundle();
            data.putInt("RESPONSE_CODE", 0);
            data.putStringArrayList("DETAILS_LIST", new ArrayList<String>());
            return data;
        }

        @Override
        public Bundle getBuyIntent(int apiVersion, String packageName, String sku, String type, String developerPayload) throws RemoteException {
            Log.d(TAG, "getBuyIntent(" + apiVersion + ", " + packageName + ", " + sku + ", " + type + ", " + developerPayload + ")");
            Bundle data = new Bundle();
            data.putInt("RESPONSE_CODE", 4);
            return data;
        }

        @Override
        public Bundle getPurchases(int apiVersion, String packageName, String type, String continuationToken) throws RemoteException {
            Log.d(TAG, "getPurchases(" + apiVersion + ", " + packageName + ", " + type + ", " + continuationToken + ")");
            Bundle data = new Bundle();
            data.putInt("RESPONSE_CODE", 0);
            data.putStringArrayList("INAPP_PURCHASE_ITEM_LIST", new ArrayList<String>());
            data.putStringArrayList("INAPP_PURCHASE_DATA_LIST", new ArrayList<String>());
            data.putStringArrayList("INAPP_DATA_SIGNATURE_LIST", new ArrayList<String>());
            return data;
        }

        @Override
        public int consumePurchase(int apiVersion, String packageName, String purchaseToken) throws RemoteException {
            Log.d(TAG, "consumePurchase(" + apiVersion + ", " + packageName + ", " + purchaseToken + ")");
            return 8;
        }

        @Override
        public Bundle getBuyIntentToReplaceSkus(int apiVersion, String packageName, List<String> oldSkus, String newSku, String type, String developerPayload) throws RemoteException {
            Log.d(TAG, "getBuyIntentToReplaceSkus(" + apiVersion + ", " + packageName + ", " + newSku + ", " + type + ", " + developerPayload + ")");
            Bundle data = new Bundle();
            data.putInt("RESPONSE_CODE", 4);
            return data;
        }

        @Override
        public Bundle queryPurchases(int apiVersion, String packageName, String type, String continuationToken, Bundle extras) throws RemoteException {
            Log.d(TAG, "queryPurchases(" + apiVersion + ", " + packageName + ", " + type + ", " + continuationToken + ")");
            Bundle data = new Bundle();
            data.putInt("RESPONSE_CODE", 0);
            data.putStringArrayList("INAPP_PURCHASE_ITEM_LIST", new ArrayList<String>());
            data.putStringArrayList("INAPP_PURCHASE_DATA_LIST", new ArrayList<String>());
            data.putStringArrayList("INAPP_DATA_SIGNATURE_LIST", new ArrayList<String>());
            return data;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (super.onTransact(code, data, reply, flags)) return true;
            Log.d(TAG, "onTransact [unknown]: " + code + ", " + data + ", " + flags);
            return false;
        }
    };

    public IBinder onBind(Intent intent) {
        return mInAppBillingService;
    }
}
