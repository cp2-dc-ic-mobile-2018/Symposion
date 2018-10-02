package br.g12.cp2.sympsion;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Palestras extends Activity {
    List<String> palestras;
    ArrayAdapter<String> adaptador;
    ListView lista;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palestras);
        lista = (ListView) findViewById(R.id.lista);
        palestras = new ArrayList<String>();
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, palestras);
        lista.setAdapter(adaptador);
        palestras.add("Palestra: NVIDIA\nAssunto: Conhecendo o NVIDIA\nDuração:1h15min\nInicio:9:00\nLocal:Roxinho\nLimite: 45 pessoas\nPalestrante: Pedro Mário Cruz");
        palestras.add("Palestra: Visualização De Dados\nAssunto: Representação de dados\nDuração:1h15min\nInicio:9:00\nLocal:Salão Nobre\nLimite: 30 pessoas\nPalestrante: Professor boladão do github");
    }

    public void logout(View view){
        SharedPreferences dadosusuario = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor myEditor = dadosusuario.edit();
        myEditor.putString("CPF", null);
        myEditor.commit();
        finish();
    }

}
