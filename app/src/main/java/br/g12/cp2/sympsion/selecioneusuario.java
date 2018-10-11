package br.g12.cp2.sympsion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class selecioneusuario extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecioneusuario);
    }

    public void teladevisitante(View view){


        // caso visitante a tela direcionará pro gerador do código qr
        Intent intent = new Intent(this, gerarQr.class);
        startActivity(intent);
    }

    public void telavoluntario(View view){

        Intent intent = new Intent(this, QrC.class);
        startActivity(intent);
    }

    public void teladepalestrante(View view){

        //como nao foi definido nenhum caso especial para o palestrante, ele tambem será direcionado a tela de gerarQR
        Intent intent = new Intent(this, gerarQr.class);
        startActivity(intent);
    }
}
