package com.example.sillas.netfoodz.gui;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sillas.netfoodz.R;
import main.java.services.UsuarioServices;

public class Cadastro extends AppCompatActivity {
    private Button cadastrar;
    private EditText editLogin;
    private UsuarioServices userService;
    private EditText editSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        try {
            userService = new UsuarioServices(getApplicationContext());
            userService.ligaBanco(getApplicationContext());
        } catch (SQLiteException e) {
            Toast.makeText(Cadastro.this, "Erro: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        editLogin = (EditText) findViewById(R.id.login);
        editSenha = (EditText) findViewById(R.id.senha);

        cadastrar = (Button) findViewById(R.id.cadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String login = editLogin.getText().toString();
                String senha = editSenha.getText().toString();

                boolean valido = validaCampos(login, senha);

                if (valido) {
                    try {
                        userService.insertuser(getApplicationContext(), login,
                                senha);
                        Toast.makeText(Cadastro.this,
                                "Cadastrado com sucesso!", Toast.LENGTH_LONG)
                                .show();
                        Intent it = new Intent(Cadastro.this, Login.class);
                        startActivity(it);
                        Cadastro.this.finish();

                    } catch (Exception e) {
                        Toast.makeText(
                                Cadastro.this,
                                "Erro: Impossivel cadastrar usuário! Este login pode já estar em uso.",
                                Toast.LENGTH_LONG).show();
                    }

                } else {

                    Toast.makeText(Cadastro.this, "Erro de validação!",
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
        });
    }
    public void cancelar(View view){
        Intent it = new Intent(Cadastro.this, Login.class);
        startActivity(it);
    }
   // public void concluircadastro(View view) {
   //     Intent it = new Intent(Cadastro.this, Login.class);
 //       startActivity(it);
 //       String mensagem = "Cadastro realizado com Sucesso";
 //       Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
 //   }
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
