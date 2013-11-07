**Get Started**

Appota Payment SDK cho Android là cách đơn giản nhất để tích hợp user
vaf thanh toán cho ứng dụng của bạn trên hệ thống Appota. SDK này cung
cấp giải pháp cho các hình thức thanh toán: SMS, thẻ cào, internet
banking, Paypal và Google Play Payment.

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

- Để bật hoặc tắt chế độ sandbox, thêm dòng sau:

    <meta-data android:name="sandbox" android:value="false" />

**3. Tích hợp SDK**

Appota Game SDK cung cấp class AppotaConfiguration cho tất cả các cấu
hình cần thiết để tích hợp Game SDK.

**Các cấu hình bắt buộc:**

- apiKey\
 - sandboxKey\
 - payment methods\
 - login methods\
 - a class inherits from AppotaLoginReceiver to get user info after
login successfully.

**Các cấu hình tùy chọn:**

- useAppotaSDKButton: turn on/off AppotaSDK button\
 - checkUpdate: auto check new updates\
 - virtualCurrencyIcon: show icon of virtual currency on SDK

**4 - Chạy SDK Samples**

Appota Game SDK cần một button để gọi lên giao diện UI được xây dựng
sẵn.

Nếu bạn muốn sử dụng nút bấm mặc định của Appota SDK:

    configuration = new AppotaConfiguration();
    configuration.setApiKey(apiKey);
    configuration.setSandboxApiKey(sandboxApiKey);
    AppotaGameSDK.getInstance().init(this, configuration);

Nếu bạn muốn sử dụng button của bạn tự thiết kế, gọi hàm sau:

    AppotaGameSDK sdk = AppotaGameSDK.getInstance().init(this, configuration);

Gọi phương thức này trong onClick handler của nút bấm của bạn:

    sdk.makePayment();

Xem thêm sample code được kèm theo bộ SDK để thêm chi tiết.
