package br.g12.cp2.sympsion;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Inicio extends Activity {
    SharedPreferences dadosusuario;
    EditText cpf;
    EditText senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        dadosusuario = PreferenceManager.getDefaultSharedPreferences(Inicio.this);
        cpf = findViewById(R.id.cpf);
        cpf.addTextChangedListener(MaskUtil.insert(cpf, MaskType.CPF));
        cpf.setFilters(new InputFilter[] {new InputFilter.LengthFilter(14)});

        senha = findViewById(R.id.senha);

        senha.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});

        if(dadosusuario.getString("CPF", null) != null){
            Intent intent = new Intent(this, Palestras.class);
            startActivity(intent);
        }
    }

    public void palestras(View view){
        Intent intent = new Intent(this, Palestras.class);
        TextView erro = findViewById(R.id.erro);
        cpf = findViewById(R.id.cpf);
        senha = findViewById(R.id.senha);
        if(cpf.length() < 11) {
            erro.setText("CPF Inválido");
        }
        else if(senha.length() < 8){
            erro.setText("A senha deve estar entre 8 a 12 caracteres");
        }
        /*
        else if(//buscar CPF do usuário no cadastro){
            erro.setText("CPF não cadastrado");
        }
        else if(//buscar senha do usuário no cadastro){
            erro.setText("Senha não cadastrada");
        }
         */
        else {
            dadosusuario = PreferenceManager.getDefaultSharedPreferences(Inicio.this);
            SharedPreferences.Editor myEditor = dadosusuario.edit();
            String campocpf = cpf.getText().toString();
            String camposenha = senha.getText().toString();
            myEditor.putString("CPF", campocpf);
            myEditor.putString("SENHA", camposenha);
            myEditor.commit();

            startActivity(intent);
        }
    }
    public void cadastrar(View view){
        /*
        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
        */
    }
    protected void onResume() {
        super.onResume();
        senha.setText("");
    }
}
