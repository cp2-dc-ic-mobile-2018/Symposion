package br.g12.cp2.sympsion;
import android.content.SharedPreferences;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
public class QrC extends Activity {
    bancoDados bd;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    long campoId;
    Button AbreS;
    List<Usuarios> participantes;
    ArrayAdapter<Usuarios> adaptador;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bd = new bancoDados(this);
        setContentView(R.layout.activity_qr_c);
        AbreS = (Button) findViewById(R.id.AbreS);
        //alert("Programa inciado");
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
        participantes = bd.pegaMeliante(campoId);

        //adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, participantes);
        adaptador = new separacaocpfnome(this, android.R.layout.simple_list_item_1, participantes);
        lista.setAdapter(adaptador);


        Intent Intentizada = getIntent();
        campoId = Intentizada.getLongExtra("ID", -999);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                //String content = result.getContents();
                //alert(content);
                alert(result.getContents());
                String string = result.getContents();

                String[] parts = string.trim().split(";");
                String part1 = parts[0]; // 004-
                String part2 = parts[1];

                TextView msgem = findViewById(R.id.msgee);


                Usuarios a = bd.buscaUserCpf(part2);

                if (a != null) {
                    //PENDENTE: Tratar casos de usuários que já foram inseridos.
                    bd.addMeliante(campoId, a.getId(), Usuarios.Papel.VISITANTE);
                    participantes = bd.pegaMeliante(campoId);
                    msgem.setText(String.valueOf(participantes.size()));
                    adaptador.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(this, "Usuário " + part2 + " não cadastrado", Toast.LENGTH_LONG).show();
                }

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
    //public void onCreate(SQLiteDatabase db) {

    //}

}