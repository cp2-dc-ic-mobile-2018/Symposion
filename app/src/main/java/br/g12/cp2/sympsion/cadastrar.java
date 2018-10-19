package br.g12.cp2.sympsion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class cadastrar extends Activity {

    bancoDados bd;
    EditText cpfUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

         bd = new bancoDados(this);
      
        cpfUsuario = findViewById(R.id.idCpf);
        cpfUsuario.addTextChangedListener(MaskUtil.insert(cpfUsuario, MaskType.CPF));
    }

    public void enviar (View view) {
        Intent intent = new Intent(this, cadastrado.class);
        EditText nomeUsuario = findViewById(R.id.idNome);
        String nome = nomeUsuario.getText().toString();

        EditText senhaUsuario = findViewById(R.id.idSenha);
        String senha = senhaUsuario.getText().toString();

        EditText emailUsuario = findViewById(R.id.idEmail);
        String email = emailUsuario.getText().toString();

        cpfUsuario = findViewById(R.id.idCpf);
        String cpf = cpfUsuario.getText().toString();

        intent.putExtra("nome", nome);
        intent.putExtra("email", email);
        intent.putExtra("cpf", cpf);

        //startActivity(intent);

        // TESTE DO CRUD //

        bd.addUsuario(new Usuarios(nome,email,cpf,senha));

        Toast.makeText(cadastrar.this, "Cadastro bem sucedido", Toast.LENGTH_LONG).show();

        List<Usuarios> usuarios = bd.selecionarUsuario();

        String msg = "";
        for (Usuarios u : usuarios) {
            msg += u.getCpf() + " - " + u.getEmail() + '\n';
        }

        TextView lista = findViewById(R.id.lista);
        lista.setText(msg);


    }
}
