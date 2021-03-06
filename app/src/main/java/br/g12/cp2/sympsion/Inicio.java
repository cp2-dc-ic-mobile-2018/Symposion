package br.g12.cp2.sympsion;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Inicio extends Activity {
    SharedPreferences dadosusuario;
    EditText cpf;
    EditText senha;
    TextView erro;
    bancoDados banco;
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

        senha.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            palestras(findViewById(R.id.login));
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        if(dadosusuario.getString("CPF", null) != null){
            Intent intent = new Intent(this, Palestras.class);
            startActivity(intent);
        }
    }

    public void palestras(View view){
        Intent intent = new Intent(this, Palestras.class);
        erro = findViewById(R.id.erro);

        cpf = findViewById(R.id.cpf);
        String campocpf = cpf.getText().toString();

        senha = findViewById(R.id.senha);
        String camposenha = senha.getText().toString();

        banco = new bancoDados(getBaseContext());
        String senhabd = banco.VerificaCPF(campocpf);

        if(cpf.length() < 11) {
            erro.setText("CPF Inválido");
        }
        else if(senha.length() < 8){
            erro.setText("A senha deve estar entre 8 a 12 caracteres");
        }
        else if(senhabd == null){
            erro.setText("CPF não cadastrado");
        }
        else if(senhabd.equals(camposenha) == false){
            erro.setText("Senha inválida");
        }

        else {
            dadosusuario = PreferenceManager.getDefaultSharedPreferences(Inicio.this);

            SharedPreferences.Editor myEditor = dadosusuario.edit();
            String nome = banco.RetornaNome(campocpf);

            myEditor.putString("NOME", nome);
            myEditor.putString("CPF", campocpf);
            myEditor.putString("SENHA", camposenha);
            myEditor.commit();

            startActivity(intent);
        }
    }
    public void cadastrar(View view){
        Intent intent = new Intent(this, cadastrar.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == cadastrar.RESULT_OK && requestCode == 1) {
            String cpf = data.getStringExtra("cpf");
            String senha = data.getStringExtra("senha");
            dadosusuario = PreferenceManager.getDefaultSharedPreferences(Inicio.this);

            SharedPreferences.Editor myEditor = dadosusuario.edit();
            banco = new bancoDados(getBaseContext());
            String nome = banco.RetornaNome(cpf);

            myEditor.putString("NOME", nome);
            myEditor.putString("CPF", cpf);
            myEditor.putString("SENHA", senha);
            myEditor.commit();

            Intent intent = new Intent(this, Palestras.class);
            startActivity(intent);
        }
    }

    protected void onResume() {
        super.onResume();
        senha.setText("");
        if(erro != null){
            erro.setText("");
        }
    }
}
