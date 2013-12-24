Other languages: [Vietnamese](README.md) | [English](README_EN.md)

**Get Started**

Android Appota Game SDK 是给你应用集成Appota 用户和支付系统的最为简洁方式。 本SDK提供： 短信、充值卡、网络银行、支付宝、Google Play Payment等各种支付方式的解决方案。

**添加SDK步骤:**


1.给project import SDK

2. 配置SDK

3. 集成SDK

3. 运行SDK samples

 

**1.给project import SDK**

下载Android的Appota Game SDK 然后import 到IDE。

**2. 配置 SDK**

**配置文件 \<AndroidMainfest.xml\>**

- 打开 文件 \<AndroidMainfest.xml\> trong project Android.

-添加以下内容以配置：

``` xml
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

-添加以下permission 配置以便使用Google Play付费面板：

``` xml
    <uses-permission android:name="com.android.vending.BILLING" />
```

-添加以下activity配置以便使用短信付费面板：

``` xml
    <activity android:name="com.appota.gamesdk.SMSPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>
```

-添加以下activity配置以便使用充值卡付费面板：

``` xml
    <activity android:name="com.appota.gamesdk.CardPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>
```

-添加以下activity配置以便使用网络银行付费面板：

``` xml
    <activity android:name="com.appota.gamesdk.BankPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>
    <activity android:name="com.appota.gamesdk.ConfirmBankPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>
```

-添加以下activity配置以便使用支付宝付费面板：


``` xml
    <activity android:name="com.appota.gamesdk.PaypalPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>
    <activity android:name="com.appota.gamesdk.ConfirmPaypalPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>
    <service android:name="com.paypal.android.sdk.payments.PayPalService" android:exported="false" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentActivity" />
    <activity android:name="com.paypal.android.sdk.payments.LoginActivity" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentMethodActivity" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentConfirmActivity" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentCompletedActivity" />
```

-添加以下activity配置以便使用Google Play Payment付费面板：


``` xml
    <activity android:name="com.appota.gamesdk.GooglePaymentActivity" android:theme="@style/Theme.Appota.GameSDK" 
    android:configChanges="orientation|keyboardHidden|screenSize"/>
```

-添加以下内容以便打开、关闭sandbox 状态：

``` xml
    <meta-data android:name="sandbox" android:value="false" />
```

-添加以下permission 配置以便使用谷歌账户登录：

``` xml
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
    <uses-permission android:name="android.permission.USE_CREDENTIALS" />
```

-添加以下permission 配置以便使用Facebook账户登录：

``` xml
    <activity android:name="com.facebook.LoginActivity" android:theme="@android:style/Theme.Translucent.NoTitleBar"
    android:label="@string/app_name" />
    <meta-data android:name="com.facebook.sdk.ApplicationId" android:value="YOUR_FACEBOOK_APP_ID" />
```
 

**3. 集成 SDK**

Appota Game SDK给所有需要的配置提供AppotaConfiguration class以便集成Game SDK

**各个必须的配置:**

 - apiKey
 - sandboxKey
 - payment methods
 - login methods
 - a class inherits from AppotaReceiver to get login/logout/payment successfully.

``` java
     private class MyReceiver extends AppotaReceiver {

        @Override
        public void onLoginSuccess(AppotaSession user) {
            //do verify login with your server now
            Toast.makeText(MainActivity.this, user.getAccessToken(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLogoutSuccess() {

        }

        //payment success callback
        @Override
        public void onPaymentSuccess(TransactionResult paymentResult) {

        }
    } 
```

** JSON配置:**

Appota Game SDK给开发商提供一个便利的配置方式。 您需要进行一下的步骤以便使用Appota Game SDK

 - 使用JSON Generator来 创造有配置的JSON 文件
(https://developer.appota.com/sdktool.php).
 -把JSON配置文件Upload 到可以访问的host
 -用到配置文件的URL初始Appota Game SDK

**初始 SDK**

把以下的code放在以下activity中onCreate()函数：

``` java
     // Register receiver to receive callback when login/logout/payment success
    MyReceiver receiver = new MyReceiver();
    IntentFilter filter = new IntentFilter();
    filter.addAction(AppotaAction.LOGIN_SUCCESS_ACTION);
    filter.addAction(AppotaAction.PAYMENT_SUCCESS_ACTION);
    registerReceiver(receiver, filter);

    // Init SDK
    AppotaGameSDK sdk = AppotaGameSDK.getInstance().init(Context context, 
    String configUrl, boolean isUseSDKButton, String noticeUrl, 
    String apiKey, String sandboxApiKey);
```

 - configUrl:到JSON配置文件的链接
 - isUseSDKButton: 关闭、打开 SDK按钮
 - noticeUrl:叫出当交易结束， 如果你已经在developer.appota.com 配置IPN就可以给""填写价值。
 - apiKey/sandboxApiKey:Appota 将你的应用提供的key 

如果不想使用 SDK的默认的floating button(isUseSDKButton = false),你也可以任意创造按钮并叫出不同的界面。

``` java
    sdk.makePayment(); // Show payment UI
```

``` java
    sdk.showUserInfo(); // Show user info UI
```

``` java
    sdk.switchAccount(); // Switch between accounts
```
 

**4 – 运行 SDK Samples**

参考SDK附件的sample code
