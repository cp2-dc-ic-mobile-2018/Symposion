package com.example.teladecadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class cadastrar extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        public void enviar (View view){
            Intent intent = new Intent(this, cadastrado.class);
            EditText nomeUsuario = findViewById(R.id.idNome);
            String nome = nomeUsuario.getText().toString();
            EditText emailUsuario = findViewById(R.id.idEmail);
            String email = emailUsuario.getText().toString();
            EditText cpfUsuario = findViewById(R.id.idCpf);
            String cpf = cpfUsuario.getText().toString();
            intent.putExtra("nome", nome);
            intent.putExtra("email", email);
            intent.putExtra("cpf", cpf);

            startActivity(intent);

        }
    }
}
