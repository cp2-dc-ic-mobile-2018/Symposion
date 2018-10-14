package br.g12.cp2.sympsion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Palestras extends Activity {
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palestras);
        lista = (ListView) findViewById(R.id.lista);

        bancoDados banco = new bancoDados(getBaseContext());

        final SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, banco.ListaPalestras(), new String[]{"nome"/*, "horario", "duração", "limitePessoas", "lugar", "descrição"*/}, new int[]{android.R.id.text1});
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = ((SimpleCursorAdapter) adaptador).getCursor();
                cursor.moveToPosition(position);
                long id_palestra = cursor.getLong(cursor.getColumnIndex(bancoDados.TabelaPalestra._ID));
                //Toast.makeText(Palestras.this, Long.toString(id_palestra), Toast.LENGTH_LONG).show();

                SharedPreferences dadosusuario = PreferenceManager.getDefaultSharedPreferences(Palestras.this);
                String nome = dadosusuario.getString("NOME", null);
                //Toast.makeText(Palestras.this, nome, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(view.getContext(), selecioneusuario.class);
                intent.putExtra("NOME", nome);
                intent.putExtra("ID", id_palestra);
                startActivity(intent);
            }
        });
    }

    public void logout(View view){
        SharedPreferences dadosusuario = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor myEditor = dadosusuario.edit();
        myEditor.putString("CPF", null);
        myEditor.commit();
        finish();
    }
}
