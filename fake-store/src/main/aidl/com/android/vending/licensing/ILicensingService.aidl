package com.android.vending.licensing;

import com.android.vending.licensing.ILicenseResultListener;

interface ILicensingService {

    void checkLicense(long someLong, String someString, in ILicenseResultListener listener);
}