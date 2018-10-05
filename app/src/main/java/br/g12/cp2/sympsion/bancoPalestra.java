package br.g12.cp2.sympsion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static br.g12.cp2.sympsion.bancoPalestra.bancoPalestra.COLUNA_DURACAO;
import static br.g12.cp2.sympsion.bancoPalestra.bancoPalestra.COLUNA_LIMITEP;
import static br.g12.cp2.sympsion.bancoPalestra.bancoPalestra.COLUNA_LUGAR;
import static br.g12.cp2.sympsion.bancoPalestra.bancoPalestra.COLUNA_NOME;
import static br.g12.cp2.sympsion.bancoPalestra.bancoPalestra.TABELA_PALESTRA;

public class bancoPalestra {

    public class bancoPalestra extends SQLiteOpenHelper {

        private static final int VERSAO_BANCO = 1;
        private static final String BANCO_PALESTRA = "bd_palestra";

        private static final String TABELA_PALESTRA = "tb_palestra";

        private static final String COLUNA_ID = "id";
        private static final String COLUNA_NOME = "nome";
        private static final String COLUNA_HORARIO = "horario";
        private static final String COLUNA_DURACAO = "duração";
        private static final String COLUNA_LIMITEP = "limitePessoas";
        private static final String COLUNA_LUGAR = "lugar";
        private static final String COLUNA_DESCRICAO = "descrição";

        public bancoPalestra(Context context) {
            super(context, BANCO_PALESTRA, null, VERSAO_BANCO);

    }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            String QUERY_COLUNA = "CREATE TABLE " + TABELA_PALESTRA + "("
                    + COLUNA_ID + " INTEGER PRIMARY KEY NOT NULL, " + COLUNA_NOME +" TEXT NOT NULL, "
                    + COLUNA_HORARIO + " TEXT NOT NULL, " + COLUNA_DURACAO + " TEXT NOT NULL, " + COLUNA_LIMITEP + " TEXT NOT NULL, "
                    + COLUNA_LUGAR + " TEXT NOT NULL, " + COLUNA_DESCRICAO + " TEXT NOT NULL)";

            sqLiteDatabase.execSQL(QUERY_COLUNA);
        }
}
    // CRUD ABAIXO //

    void addPalestra(Palestras palestras)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, palestras.getNome());
        values.put(COLUNA_DURACAO, palestras.get());
        values.put(COLUNA_LUGAR, palestras.get());
        values.put(COLUNA_LIMITEP, palestras.get());

        sqLiteDatabase.insert(TABELA_PALESTRA, null, values);
        sqLiteDatabase.close();

    }
