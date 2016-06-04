package com.example.sillas.netfoodz.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sillas.netfoodz.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Login extends AppCompatActivity {
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_login);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile", "user_friends");
        getLoginDetails(loginButton);
    }
    protected void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }
    protected void getLoginDetails(LoginButton login_button){
        // Callback registration
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult login_result) {
                Intent intent = new Intent(Login.this,Listar_produtos.class);
                startActivity(intent);
            }
            @Override
            public void onCancel() {
                Toast.makeText(Login.this, "Necessario Logar para Usar o APP",
                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(Login.this, "Erro: " + exception.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    public void ircadastrese (View v){
        Intent it = new Intent(Login.this, Cadastro.class);
        startActivity(it);
    }
    public void irtelaproduto (View v){
        Intent it = new Intent(Login.this, Listar_produtos.class);
        startActivity(it);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("data",data.toString());
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

}

