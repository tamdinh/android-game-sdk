
package com.appota.sample.gamesdk;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.appota.gamesdk.commons.AppotaAction;
import com.appota.gamesdk.core.AppotaConfiguration;
import com.appota.gamesdk.core.AppotaGameSDK;
import com.appota.gamesdk.core.AppotaPayment;
import com.appota.gamesdk.core.AppotaReceiver;
import com.appota.gamesdk.core.AppotaUser;
import com.appota.gamesdk.model.AppotaSession;
import com.appota.gamesdk.model.CardPaymentResult;
import com.appota.gamesdk.model.TransactionResult;

public class MainActivity extends Activity {

    private String apiKey = "123593a5f93eac19e26baee408f9928f0525e6a18";
    private String sandboxApiKey = "";
    private AppotaConfiguration configuration;
    private AppotaGameSDK sdk;
    //implement login recveive to start verify user on your server
    private LoginRecevier recevier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        recevier = new LoginRecevier();
        IntentFilter filter = new IntentFilter();
        filter.addAction(AppotaAction.LOGIN_SUCCESS_ACTION);
        filter.addAction(AppotaAction.LOGOUT_SUCCESS_ACTION);
        filter.addAction(AppotaAction.CARD_PAYMENT_SUCCESS_ACTION);
        filter.addAction(AppotaAction.PAYMENT_SUCCESS_ACTION);
        registerReceiver(recevier, filter);

        //config for Appota SDK
        configuration = new AppotaConfiguration();
        configuration.setApiKey(apiKey);
        configuration.setSandboxApiKey(sandboxApiKey);

        //determine use AppotaSDKButton or not
        configuration.setUseAppotaSDKButton(true);

        //determine auto check for new version
        configuration.setCheckUpdate(true);

        //set your virtual currency image for display in SDK
        configuration.setVirtualCurrencyIconRes(R.drawable.ic_virtual_currency);

        //add amount and payment method
        configuration.addSupportPayment(new AppotaPayment(5000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_SMS, 50));
        configuration.addSupportPayment(new AppotaPayment(10000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_SMS, 100));
        configuration.addSupportPayment(new AppotaPayment(15000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_SMS, 150));

        configuration.addSupportPayment(new AppotaPayment(20000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_INTERNET_BANKING, 250));
        configuration.addSupportPayment(new AppotaPayment(50000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_INTERNET_BANKING, 520));
        configuration.addSupportPayment(new AppotaPayment(100000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_INTERNET_BANKING, 1100));

        configuration.addSupportPayment(new AppotaPayment(0.99, AppotaPayment.CURRENCY_USD, AppotaPayment.PAYMENT_PAYPAL, 600));
        configuration.addSupportPayment(new AppotaPayment(1.99, AppotaPayment.CURRENCY_USD, AppotaPayment.PAYMENT_PAYPAL, 1200));
        configuration.addSupportPayment(new AppotaPayment(2.99, AppotaPayment.CURRENCY_USD, AppotaPayment.PAYMENT_PAYPAL, 1800));

        configuration.addSupportPayment(new AppotaPayment(10000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_CARD, 100));
        configuration.addSupportPayment(new AppotaPayment(20000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_CARD, 210));
        configuration.addSupportPayment(new AppotaPayment(50000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_CARD, 550));
        configuration.addSupportPayment(new AppotaPayment(100000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_CARD, 1080));
        configuration.addSupportPayment(new AppotaPayment(200000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_CARD, 2200));
        configuration.addSupportPayment(new AppotaPayment(500000, AppotaPayment.CURRENCY_VND, AppotaPayment.PAYMENT_CARD, 5700));

        //add supported login method
        configuration.addSupportLoginMethod(AppotaUser.LOGIN_APPOTA);
        //configuration.addSupportLoginMethod(AppotaUser.LOGIN_GOOGLE);
        configuration.addSupportLoginMethod(AppotaUser.LOGIN_APPOTA_FAST);
        configuration.addSupportLoginMethod(AppotaUser.LOGIN_SOCIALS);

        configuration.setState("state");
        configuration.setNoticeUrl("http://filestore9.com/test.php");

        //if use AppotaSDKButton
        if(configuration.isUseAppotaSDKButton()){
            configuration.setButtonSize(48);
        }
        sdk = AppotaGameSDK.getInstance().init(this, configuration);

        //getUserID
        String userId = AppotaUser.getUserId(this);
        //getUsername
        String username = AppotaUser.getUserName(this);
    }

    //if not use AppotaSDKButton, call makePayment() in an event. for example, on button click
    public void makePayment(View v){
        sdk.makePayment();
    }

    //implement login recveive to start verify user on your server
    private class LoginRecevier extends AppotaReceiver {

        @Override
        public void onLoginSuccess(AppotaSession user) {
            //do verify login with your server now
            Toast.makeText(MainActivity.this, "do verify login with your server now", Toast.LENGTH_SHORT).show();
        }

		@Override
		public void onCardPaymentSuccess(CardPaymentResult arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onLogoutSuccess() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPaymentSuccess(TransactionResult arg0) {
			// TODO Auto-generated method stub
			
		}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(recevier);
    }
}
