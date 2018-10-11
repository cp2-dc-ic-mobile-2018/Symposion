package br.g12.cp2.sympsion;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
public class QrC extends Activity {
    Button AbreS;
    int x = 0;
    List<String> participantes;
    ArrayAdapter<String> adaptador;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_c);
        AbreS = (Button) findViewById(R.id.AbreS);
        alert("Programa inciado");
        final Activity activity = this;
        AbreS.setOnClickListener(new View.OnClickListener() {

                                     @Override
                                     public void onClick(View view) {
                                         IntentIntegrator integrator = new IntentIntegrator(activity);
                                         integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                                         integrator.setPrompt("Scanner");
                                         integrator.setCameraId(0);
                                         integrator.initiateScan();
                                     }
                                 }
        );
        lista = (ListView) findViewById(R.id.lista);
        participantes = new ArrayList<String>();

        //adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, participantes);
        adaptador = new separacaocpfnome(this, android.R.layout.simple_list_item_1, participantes);
        lista.setAdapter(adaptador);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                //String content = result.getContents();
                //alert(content);
                alert(result.getContents());
                String string = result.getContents();

                participantes.add(string);

                //String[] parts = string.split(";");
                //String part1 = parts[0]; // 004-
                //String part2 = parts[1];
                //participantes.add("Nome do meliante: " + part1 + " (CPF: " + part2 + ")");

                adaptador.notifyDataSetChanged();
                x++;
                TextView msgem = findViewById(R.id.msgee);
                msgem.setText(String.valueOf(x));
            }
            else{
                alert("Scanner cancelado");
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    //String metodo(String parametro){
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + TABELA_PALESTRAUSUARIO + "(COLUNA_IDUSUARIO, COLUNA_IDPALESTRA) VALUES ( id, usuario )");
    }
        /*String selectQuery =
                "SELECT * FROM usuario WHERE cpf =" + parametro;
        BancoDados  Bd =  new BancoDados(this);
        SQLiteDatabase banco = Bd.getWritableDatabase();

        Cursor cursor = banco.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        String nomeString = cursor.getString(cursor.getColumnIndex('id'));
        return nomeString;*/
    //}
}