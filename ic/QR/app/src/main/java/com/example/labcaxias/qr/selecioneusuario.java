package com.example.labcaxias.qr;

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

        Intent intent = new Intent(this, QrC.class);
        startActivity(intent);
    }

    public void telavoluntario(View view){

        Intent intent = new Intent(this, QrC.class);
        startActivity(intent);
    }

    public void teladepalestrante(View view){

        Intent intent = new Intent(this, QrC.class);
        startActivity(intent);
    }
}
