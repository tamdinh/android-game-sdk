
package com.appota.sample.gamesdk;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.appota.gamesdk.commons.AppotaAction;
import com.appota.gamesdk.core.AppotaGameSDK;
import com.appota.gamesdk.core.AppotaReceiver;
import com.appota.gamesdk.model.AppotaSession;
import com.appota.gamesdk.model.TransactionResult;

public class MainActivity extends Activity {

    private String apiKey = "123593a5f93eac19e26baee408f9928f0525e6a18";
    private String sandboxApiKey = "";
    private AppotaGameSDK sdk;
    private LoginReceiver recevier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //register receiver for receive payment and login event
        recevier = new LoginReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(AppotaAction.LOGIN_SUCCESS_ACTION);
        filter.addAction(AppotaAction.PAYMENT_SUCCESS_ACTION);
        registerReceiver(recevier, filter);
        //init sdk
        sdk = AppotaGameSDK.getInstance().init(this, "http://filestore9.com/config.php", true, "http://filestore9.com/test.php", apiKey, sandboxApiKey);
        sdk.setShowButtonType(AppotaGameSDK.SHOW_ACCOUNT_BUTTON);
    }

    //if not use AppotaSDKButton, call makePayment() in an event. for example, on button click
    public void makePayment(View v){
        sdk.setState("state1");
        sdk.makePayment();
    }

    public void showUserInfo(View v){
        sdk.showUserInfo();
    }

    public void switchAccount(View v){
        sdk.switchAccount();
    }

    public void logout(View v){
        sdk.logout(true);
    }

    //implement login recveive to start verify user on your server
    private class LoginReceiver extends AppotaReceiver {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sdk.finish();
        unregisterReceiver(recevier);
    }

}
