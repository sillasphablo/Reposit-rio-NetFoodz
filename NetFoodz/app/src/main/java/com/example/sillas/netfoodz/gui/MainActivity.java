package com.example.sillas.netfoodz.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sillas.netfoodz.R;

import static com.example.sillas.netfoodz.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        Button cadastrese = (Button) findViewById(R.id.cadastrese);
    }
    public void ircadastrese(View view) {
        Intent it = new Intent(MainActivity.this, Cadastro.class);
        startActivity(it);
    }
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
