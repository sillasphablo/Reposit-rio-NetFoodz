package com.example.sillas.netfoodz.gui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.sillas.netfoodz.R;

public class Listar_produtos extends AppCompatActivity implements Carro_fragment.OnFragmentInteractionListener{
    Button button;
//    List<String> opcoes;
//    ArrayAdapter<String> adaptador;
//    ListView lvOpcoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produto);
//      lvOpcoes = (ListView) findViewById(R.id.lvopcoes)
//      opcoes = new ArrayList<String>();
//      adaptador = new ArrayAdapter<String>(Listar_produtos.this, android.R.layout.simple_list_item_1, opcoes);
//      lvOpcoes.setAdapter(adaptador);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void ircarrinho(MenuItem v) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Carro_fragment cf = new Carro_fragment();
        ft.add(R.id.activity_listar_produto, cf);
        ft.commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_carrinho:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

