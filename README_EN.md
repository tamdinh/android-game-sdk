Other languages: [Vietnamese](README.md) | [Chinese](README_CN.md)

**Get Started**

Appota Game SDK is the simplest way to integrate user and payment for
your game in Appota system. This SDK provides solutions for payment
methods such as: SMS, Card. Internet Banking, Paypal and Google Play
Payment.

**Steps to integrate SDK:**

​1. Import SDK into your project

​2. Configure SDK

​3. Integrate SDK

 4. Run SDK samples

 

**1. Import SDK into project**

Download Appota Game SDK for Android and import into IDE.

**2. Configure SDK**

**Configuration \<AndroidMainfest.xml\>**

- Open file \<AndroidMainfest.xml\> in your project Android.

- Add following lines to configure permission:

``` xml
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

- To use SMS payment interface, add following activity configuration:

``` xml
    <activity android:name="com.appota.gamesdk.SMSPaymentActivity" 
    android:theme="@style/Theme.Appota.GameSDK" 
    android:configChanges="orientation|keyboardHidden|screenSize"/>
```

- To use Card payment interface, add following activity configuration:

``` xml
    <activity android:name="com.appota.gamesdk.CardPaymentActivity" 
    android:theme="@style/Theme.Appota.GameSDK" 
    android:configChanges="orientation|keyboardHidden|screenSize"/>
```

- To use Internet Banking payment interface, add following acticity
configuration:

``` xml
    <activity android:name="com.appota.gamesdk.BankPaymentActivity" 
    android:theme="@style/Theme.Appota.GameSDK" 
    android:configChanges="orientation|keyboardHidden|screenSize"/>
    <activity android:name="com.appota.gamesdk.ConfirmBankPaymentActivity" 
    android:theme="@style/Theme.Appota.GameSDK" 
    android:configChanges="orientation|keyboardHidden|screenSize"/>
```

- To use Paypal payment interface, add following activity configuration:

``` xml
    <activity android:name="com.appota.gamesdk.PaypalPaymentActivity" 
    android:theme="@style/Theme.Appota.GameSDK" 
    android:configChanges="orientation|keyboardHidden|screenSize"/>
    <activity android:name="com.appota.gamesdk.ConfirmPaypalPaymentActivity" 
    android:theme="@style/Theme.Appota.GameSDK" 
    android:configChanges="orientation|keyboardHidden|screenSize"/>
    <service android:name="com.paypal.android.sdk.payments.PayPalService" 
    android:exported="false" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentActivity" />
    <activity android:name="com.paypal.android.sdk.payments.LoginActivity" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentMethodActivity" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentConfirmActivity" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentCompletedActivity" />
```

- To turn off or on Sandbox mode, add following configuration:

``` xml
    <meta-data android:name="sandbox" android:value="false" />
```

**3. Integrate SDK**

Appota Game SDK provides class AppotaConfiguration for all needed configuration to integrate Game SDK.

**Required configurations:**

 - apiKey
 - sandboxKey
 - payment methods
 - login methods
 - a class inherits from AppotaReceiver to get user info after login successfully.

**JSON configurations:**

Appota Game SDK provides a flexible method to configure various options. You need flow these steps to use this method:

 - Generate a JSON config file
 - Upload your config file to an accessible host.
 - Pass it as a param when init Appota Game SDK.

**4 - Run SDK Samples**

Appota Game SDK need a button to show all flow in only one UI.

Call this method in onCreate() function of activity:

``` java
    AppotaGameSDK sdk = AppotaGameSDK.getInstance().init(Context context, 
        String configUrl, boolean isUseSDKButton, String noticeUrl, 
        String apiKey, String sandboxApiKey);
```

- configUrl: Link to JSON config file.
- isUseSDKButton: On/off default sdk buttton
- noticeUrl: Called when a transaction is finished, if you have already config IPN on developer site, just pass ""
- apiKey/sandboxApiKey: Provided by Appota for your application.

In the case you don't use default SDK button (isUseSDKButton = false), you can create your custom buttons to
call separate UI:


``` java
    sdk.makePayment(); // Show payment UI
```
``` java
    sdk.showUserInfo(); // Show user info UI
```
``` java
    sdk.switchAccount(); // Switch between accounts
```
``` java
    sdk.logout(boolean isShowLoginWhenLoggedOut); // Logout with option show/hide login popup after logged out
```

You can see the more detail in the attached sample code.

    AppotaGameSDK.getInstance().setState(String state) // hide this
