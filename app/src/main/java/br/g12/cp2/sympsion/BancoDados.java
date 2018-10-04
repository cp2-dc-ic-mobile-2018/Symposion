package br.g12.cp2.sympsion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_USUARIO = "bd_usuario";

    private static final String TABELA_USUARIO = "tb_usuario";

    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_CPF = "cpf";
    private static final String COLUNA_SENHA = "senha";

    public BancoDados(Context context) {
        super(context, BANCO_USUARIO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_USUARIO + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY NOT NULL, " + COLUNA_NOME +" TEXT NOT NULL, "
                + COLUNA_EMAIL + " TEXT NOT NULL, " + COLUNA_CPF + " TEXT NOT NULL, " + COLUNA_SENHA + " TEXT NOT NULL) ";

        sqLiteDatabase.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // CRUD ABAIXO //

    void addUsuario(Usuarios usuarios)
    {
       SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

       ContentValues values = new ContentValues();

       values.put(COLUNA_NOME, usuarios.getNome());
        values.put(COLUNA_EMAIL, usuarios.getEmail());
       values.put(COLUNA_CPF, usuarios.getCpf());
       values.put(COLUNA_SENHA, usuarios.getSenha());

       sqLiteDatabase.insert(TABELA_USUARIO, null, values);
       sqLiteDatabase.close();

    }


    List<Usuarios> selecionarUsuario() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                TABELA_USUARIO,
                new String[] { COLUNA_CPF, COLUNA_EMAIL },
                null,
                null, null, null, null);


        List<Usuarios> lista = new ArrayList<Usuarios>();
        while(cursor.moveToNext()) {
            String cpf = cursor.getString(0);
            String email = cursor.getString(1);
            Usuarios usuarios1 = new Usuarios();
            usuarios1.setCpf(cpf);
            usuarios1.setEmail(email);
            lista.add(usuarios1);
        }
        return lista;

   }
}


