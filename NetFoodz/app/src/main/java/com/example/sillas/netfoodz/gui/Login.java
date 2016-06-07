package main.java.com.example.sillas.netfoodz.gui;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sillas.netfoodz.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import main.java.services.UsuarioServices;

public class Login extends AppCompatActivity {
    CallbackManager callbackManager;
    private Button entrar;
    private EditText editLogin;
    private EditText editSenha;
    private UsuarioServices userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_login);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email","public_profile","user_friends");
        getLoginDetails(loginButton);

        try {
            userService = new UsuarioServices(getApplicationContext());
            userService.ligaBanco(getApplicationContext());
        } catch (SQLiteException e) {
            Toast.makeText(Login.this, "Erro: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        editLogin = (EditText) findViewById(R.id.login);
        editSenha = (EditText) findViewById(R.id.senha);

        // Botão para fazer login
        entrar = (Button) findViewById(R.id.entrar);
        entrar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String login = editLogin.getText().toString();
                String senha = editSenha.getText().toString();

                boolean valido = validaCampos(login, senha);

                if (valido) {
                    try {
                        boolean correto = userService.verificauser(
                                getApplicationContext(), login, senha);
                        if (correto) {
                            Intent NetFoodz = new
                                    Intent(Login.this, Listar_produtos.class);
                            Login.this.startActivity(NetFoodz);
                            Login.this.finish();
                        } else {
                            Toast.makeText(Login.this,
                                    "Login e/ou senha incorretos!",
                                    Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(
                                Login.this,
                                "Erro: Impossivel cadastrar usuário! Este login pode já estar em uso.",
                                Toast.LENGTH_LONG).show();
                    }

                } else {

                    Toast.makeText(Login.this, "Erro de validação!",
                            Toast.LENGTH_LONG).show();
                }

            }

            // Método para validar campos
            public boolean validaCampos(String login, String senha) {
                boolean result = false;
                if (login.equals("") || senha.equals("")) {
                    result = false;
                } else {
                    result = true;
                }
                return result;
            }
        });}


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
                // code for cancellation
            }

            @Override
            public void onError(FacebookException exception) {
                //  code to handle error
            }
        });
    }

    public void ircadastrese (View v){
        Intent it = new Intent(Login.this, Cadastro.class);
        startActivity(it);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("data",data.toString());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
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

