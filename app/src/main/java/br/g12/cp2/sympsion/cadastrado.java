package br.g12.cp2.sympsion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class cadastrado extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrado);

        Intent dados = getIntent();
        String nomeUsuario =  dados.getStringExtra("nome");
        String senhaUsuario =  dados.getStringExtra("senha");
        String emailUsuario = dados.getStringExtra("email");
        String cpfUsuario = dados.getStringExtra("cpf");
        TextView msg = findViewById(R.id.msg);
        msg.setText("Usuário " + nomeUsuario +" cadastrado com sucesso!" );
    }
}
