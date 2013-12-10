**Get Started**

Appota Game SDK cho Android là cách đơn giản nhất để tích hợp user vaf
thanh toán cho ứng dụng của bạn trên hệ thống Appota. SDK này cung cấp
giải pháp cho các hình thức thanh toán: SMS, thẻ cào, internet banking,
Paypal và Google Play Payment.

**Các bước tích hợp SDK:**

​1. Import SDK vào project của bạn

​2. Cấu hình SDK

​3. Tích hợp SDK

​3. Chạy SDK samples

 

**1. Import SDK vào project của bạn**

Download Appota Game SDK cho Android và import vào IDE.

**2. Cấu hình SDK**

**Cấu hình file \<AndroidMainfest.xml\>**

- Mở file \<AndroidMainfest.xml\> trong project Android.

- Thêm những dòng sau để cấu hình phân quyền:

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

- Để sử dụng giao diện thanh toán Google Play, thêm cấu hình permission
sau:

    <uses-permission android:name="com.android.vending.BILLING" />

- Để sử dụng giao diện thanh toán SMS, thêm cấu hình activity sau:

    <activity android:name="com.appota.gamesdk.SMSPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>

- Để sử dụng giao diện thanh toán thẻ cào, thêm cấu hình activity sau:

    <activity android:name="com.appota.gamesdk.CardPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>

- Để sử dụng giao diện thanh toán Internet Banking, thêm cấu hình
activity sau:

    <activity android:name="com.appota.gamesdk.BankPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>
    <activity android:name="com.appota.gamesdk.ConfirmBankPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>

- Để sử dụng giao diện thanh toán Paypal, thêm cấu hình activity sau:

    <activity android:name="com.appota.gamesdk.PaypalPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>
    <activity android:name="com.appota.gamesdk.ConfirmPaypalPaymentActivity" android:theme="@style/Theme.Appota.GameSDK" android:configChanges="orientation|keyboardHidden|screenSize"/>
    <service android:name="com.paypal.android.sdk.payments.PayPalService" android:exported="false" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentActivity" />
    <activity android:name="com.paypal.android.sdk.payments.LoginActivity" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentMethodActivity" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentConfirmActivity" />
    <activity android:name="com.paypal.android.sdk.payments.PaymentCompletedActivity" />

- Để sử dụng giao diện thanh toán Google Play Payment, thêm cấu hình
activity sau:

    <activity android:name="com.appota.gamesdk.GooglePaymentActivity" android:theme="@style/Theme.Appota.GameSDK" 
    android:configChanges="orientation|keyboardHidden|screenSize"/>

- Để bật hoặc tắt chế độ sandbox, thêm dòng sau:

    <meta-data android:name="sandbox" android:value="false" />

- Để sử dụng tài khoản Google để đăng nhập, thêm cấu hình permission
sau:

    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
    <uses-permission android:name="android.permission.USE_CREDENTIALS" />

- Để sử dụng tài khoản Facebook để đăng nhập, thêm cấu hình permission
sau:

    <activity android:name="com.facebook.LoginActivity" android:theme="@android:style/Theme.Translucent.NoTitleBar"
    android:label="@string/app_name" />
    <meta-data android:name="com.facebook.sdk.ApplicationId" android:value="YOUR_FACEBOOK_APP_ID" />

 

**3. Tích hợp SDK**

Appota Game SDK cung cấp class AppotaConfiguration cho tất cả các cấu
hình cần thiết để tích hợp Game SDK.

**Các cấu hình bắt buộc:**

- apiKey\
 - sandboxKey\
 - payment methods\
 - login methods\
 - a class inherits from AppotaReceiver to get login/logout/payment
successfully.

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

**Cấu hình JSON:**

Appota Game SDK cung cấp một phương thức cấu hình tiện lợi với nhiều tùy
chọn cho nhà phát triển. Bạn cần thực hiện các bước sau để sử dụng
phương thức này:

- Sử dụng công cụ JSON Generator để tạo ra file JSON chứa các cấu hình.\
 - Upload file cấu hình JSON lên một host có thể truy cập được.\
 - Khởi tạo Appota Game SDK với URL tới file cấu hình.

**Khởi tạo SDK**

Đặt đoạn mã sau trong hàm onCreate() của activity:

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

- configUrl: Link tới file cấu hình JSON.\
 - isUseSDKButton: Tắt/bật nút SDK.\
 - noticeUrl: Được gọi khi một transaction kết thúc, nếu bạn đã cấu hình
IPN trên trang developer có thể truyền giá trị "" vào.\
 - apiKey/sandboxApiKey: Các key được cung cấp bởi Appota cho ứng dụng
của bạn.

Trong trường hợp không muốn sử dụng nút nổi mặc định của SDK
(isUseSDKButton = false), bạn có thể tạo nút bấm tùy chọn và gọi các
giao diện riêng biệt:

    sdk.makePayment(); // Show payment UI

    sdk.showUserInfo(); // Show user info UI

    sdk.switchAccount(); // Switch between accounts

 

**4 - Chạy SDK Samples**

Xem thêm sample code được kèm theo bộ SDK để thêm chi tiết.
