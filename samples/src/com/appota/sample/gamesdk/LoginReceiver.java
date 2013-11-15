package com.appota.sample.gamesdk;


import com.appota.gamesdk.core.AppotaReceiver;
import com.appota.gamesdk.model.AppotaSession;
import com.appota.gamesdk.model.CardPaymentResult;
import com.appota.gamesdk.model.TransactionResult;

public class LoginReceiver extends AppotaReceiver {

    @Override
    public void onLoginSuccess(AppotaSession user) {
        //Toast.makeText(MainActivity.this, "do verify login with your server now", Toast.LENGTH_SHORT).show();
        System.err.println(user.getUsername() + " " + user.getUserId());
    }

    @Override
    public void onLogoutSuccess() {

    }

    @Override
    public void onPaymentSuccess(TransactionResult paymentResult) {

    }
}
