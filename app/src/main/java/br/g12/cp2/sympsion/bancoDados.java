package br.g12.cp2.sympsion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


import java.util.ArrayList;
import java.util.List;

public class bancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;

    private static final String NOME_BANCO = "bd_symposion";

    //TABELA DO CADASTRO//
    class TabelaUsuario implements BaseColumns {
        private static final String TABELA_USUARIO = "tb_usuario";

        private static final String COLUNA_NOME = "nome";
        private static final String COLUNA_EMAIL = "email";
        private static final String COLUNA_CPF = "cpf";
        private static final String COLUNA_SENHA = "senha";
    }

    //TABELA DA PALESTRA
    class TabelaPalestra implements BaseColumns {
        private static final String TABELA_PALESTRA = "tb_palestra";

        private static final String COLUNA_NOME = "nome";
        private static final String COLUNA_HORARIO = "horario";
        private static final String COLUNA_DURACAO = "duração";
        private static final String COLUNA_LIMITEP = "limitePessoas";
        private static final String COLUNA_LUGAR = "lugar";
        private static final String COLUNA_DESCRICAO = "descrição";
    }

    //TABELA DO RELACIONAMENTO ENTRE TABELA USUARIO
    class TabelaPalestraUsuario {
        private static final String TABELA_PALESTRAUSUARIO = "tb_palestraUsuario";

        private static final String COLUNA_IDUSUARIO = "idUsuario";
        private static final String COLUNA_IDPALESTRA = "idPalestra";
        private static final String COLUNA_PAPEL = "papel";


    }




    public bancoDados(Context context) {
        super(context, NOME_BANCO,null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String QUERY_COLUNA = "CREATE TABLE " + TabelaUsuario.TABELA_USUARIO + "("
                + TabelaUsuario._ID + " INTEGER PRIMARY KEY NOT NULL, "
                + TabelaUsuario.COLUNA_NOME +" TEXT NOT NULL, "
                + TabelaUsuario.COLUNA_EMAIL + " TEXT NOT NULL UNIQUE, "
                + TabelaUsuario.COLUNA_CPF + " TEXT NOT NULL UNIQUE, "
                + TabelaUsuario.COLUNA_SENHA + " TEXT NOT NULL) ";

        sqLiteDatabase.execSQL(QUERY_COLUNA);
        criaUsuarioPadrao(sqLiteDatabase);


         QUERY_COLUNA = "CREATE TABLE " + TabelaPalestra.TABELA_PALESTRA + "("
                 + TabelaPalestra._ID + " INTEGER PRIMARY KEY NOT NULL, "
                 + TabelaPalestra.COLUNA_NOME + " TEXT NOT NULL, "
                 + TabelaPalestra.COLUNA_HORARIO + " TEXT NOT NULL, "
                 + TabelaPalestra.COLUNA_DURACAO + " INTEGER NOT NULL, "
                 + TabelaPalestra.COLUNA_LIMITEP + " INTEGER NOT NULL, "
                 + TabelaPalestra.COLUNA_LUGAR + " TEXT NOT NULL, "
                 + TabelaPalestra.COLUNA_DESCRICAO + " TEXT NOT NULL) ";

        sqLiteDatabase.execSQL(QUERY_COLUNA);

        criaPalestrasTeste(sqLiteDatabase);

        QUERY_COLUNA = "CREATE TABLE " + TabelaPalestraUsuario.TABELA_PALESTRAUSUARIO + "("
                + TabelaPalestraUsuario.COLUNA_IDUSUARIO + " INTEGER NOT NULL, "
                + TabelaPalestraUsuario.COLUNA_IDPALESTRA + " INTEGER NOT NULL, "
                + TabelaPalestraUsuario.COLUNA_PAPEL + " INTEGER NOT NULL, "
                + "PRIMARY KEY (" + TabelaPalestraUsuario.COLUNA_IDUSUARIO + ", " + TabelaPalestraUsuario.COLUNA_IDPALESTRA + "), "
                + "FOREIGN KEY (" + TabelaPalestraUsuario.COLUNA_IDUSUARIO + ") REFERENCES " + TabelaUsuario.TABELA_USUARIO + " (" + TabelaUsuario._ID + "),"
                + "FOREIGN KEY (" + TabelaPalestraUsuario.COLUNA_IDPALESTRA + ") REFERENCES " + TabelaPalestra.TABELA_PALESTRA + " ( " + TabelaPalestra._ID + "))"   ;

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

       values.put(TabelaUsuario.COLUNA_NOME, usuarios.getNome());
        values.put(TabelaUsuario.COLUNA_EMAIL, usuarios.getEmail());
       values.put(TabelaUsuario.COLUNA_CPF, usuarios.getCpf());
       values.put(TabelaUsuario.COLUNA_SENHA, usuarios.getSenha());

       sqLiteDatabase.insert(TabelaUsuario.TABELA_USUARIO, null, values);
       sqLiteDatabase.close();

    }


    List<Usuarios> selecionarUsuario() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                TabelaUsuario.TABELA_USUARIO,
                new String[] { TabelaUsuario.COLUNA_CPF, TabelaUsuario.COLUNA_EMAIL },
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

    public Usuarios buscaUserCpf(String cpf)
    {

        String campocpf;
        String[] selectionArgs = {cpf};
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TabelaUsuario.TABELA_USUARIO, null, TabelaUsuario.COLUNA_CPF + " = ?", selectionArgs, null, null, null);

        Usuarios usuarios1;
        if (cursor.moveToNext()) {
            usuarios1 = new Usuarios();
            usuarios1.setCpf(cursor.getString(3));
            usuarios1.setEmail(cursor.getString(2));
            usuarios1.setId(cursor.getInt(0));
            usuarios1.setNome(cursor.getString(1));
            usuarios1.setSenha(cursor.getString(4));
        }
        else {
            usuarios1 = null;
        }

        sqLiteDatabase.close();
        return usuarios1;
    }


   private void criaPalestrasTeste(SQLiteDatabase sqLiteDatabase) {
        List<dadosPalestra> palestras = new ArrayList<dadosPalestra>();

        palestras.add(new dadosPalestra("NVIDIA", "9:00", 75, 45, "Roxinho", "Conhecendo NVIDIA" ));
       palestras.add(new dadosPalestra("Intel", "8:00", 86, 70, "Rosinha", "Processadores"));

       for (dadosPalestra palestra : palestras) {
           ContentValues values = new ContentValues();

           values.put(TabelaPalestra.COLUNA_NOME, palestra.getNome());
           values.put(TabelaPalestra.COLUNA_HORARIO, palestra.getHorario());
           values.put(TabelaPalestra.COLUNA_DURACAO, palestra.getDuracao());
           values.put(TabelaPalestra.COLUNA_LIMITEP, palestra.getLimiteP());
           values.put(TabelaPalestra.COLUNA_LUGAR, palestra.getLugar());
           values.put(TabelaPalestra.COLUNA_DESCRICAO, palestra.getDescricao());

           sqLiteDatabase.insert(TabelaPalestra.TABELA_PALESTRA, null, values);
       }
   }

    private void criaUsuarioPadrao(SQLiteDatabase sqLiteDatabase) {

        List<Usuarios> usuarios = new ArrayList<Usuarios>();

        usuarios.add(new Usuarios("Jeremilson", "jeremilson@example.net", "12345678901", "androidic"));
        usuarios.add(new Usuarios("Hermenegildo", "hermenegildo@example.net", "09876543212", "icandroid"));
        usuarios.add(new Usuarios("Benevides", "benevides@example.net", "32145678909", "devandroid"));

        for (Usuarios usuario : usuarios) {
            ContentValues values = new ContentValues();
            values.put(TabelaUsuario.COLUNA_NOME, usuario.getNome());
            values.put(TabelaUsuario.COLUNA_EMAIL, usuario.getEmail());
            values.put(TabelaUsuario.COLUNA_CPF, usuario.getCpf());
            values.put(TabelaUsuario.COLUNA_SENHA, usuario.getSenha());

            sqLiteDatabase.insert(TabelaUsuario.TABELA_USUARIO, null, values);

        }
    }

    public String VerificaCPF(String cpf) {
        Cursor cursor;
        String senha;
        String[] campos = {TabelaUsuario.COLUNA_CPF, TabelaUsuario.COLUNA_SENHA};
        String[] selectionArgs = {cpf};
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        cursor = sqLiteDatabase.query(TabelaUsuario.TABELA_USUARIO, campos, TabelaUsuario.COLUNA_CPF + " = ?", selectionArgs, null, null, null);

        if (cursor.moveToNext()) {
            senha = cursor.getString(1);
        } else {
            senha = null;
        }
        sqLiteDatabase.close();
        return senha;
    }
}


