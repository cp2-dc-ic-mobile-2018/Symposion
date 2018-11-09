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

public class Palestras extends Activity {
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palestras);
        lista = findViewById(R.id.lista);

        bancoDados banco = new bancoDados(getBaseContext());

        final SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this, R.layout.lista, banco.ListaPalestras(), new String[]{"nome", "horario", "duração", "limitePessoas", "lugar", "descrição"}, new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6});
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = adaptador.getCursor();
                cursor.moveToPosition(position);
                long id_palestra = cursor.getLong(cursor.getColumnIndex(bancoDados.TabelaPalestra._ID));

                SharedPreferences dadosusuario = PreferenceManager.getDefaultSharedPreferences(Palestras.this);
                String nome = dadosusuario.getString("NOME", null);

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
