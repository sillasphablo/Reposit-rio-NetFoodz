package persistencia;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class NetFoodzSQLiteHelper extends SQLiteOpenHelper {

    // ////////////////////////////////////////////////////////////////////////////////////////////////////
    // ATRIBUTOS DA TABELA DE USUÁRIOS

    // Nome da tabela a ser criada
    public static final String TABELA_USUARIO = "usuarios";

    // Colunas da tabela USUARIOS
    public static final String COLUNA_ID_USUARIO = "_id";
    public static final String COLUNA_NOME_USUARIO = "usuario";
    public static final String COLUNA_SENHA_USUARIO = "senha";

    // Nome do banco de dados
    private static final String DATABASE_NAME = "minhahorta.sqlite";

    // Versao atual do banco de dados (Utilizada para controle e atualizacao de
    // bancos posteriormente)
    private static final int DATABASE_VERSION = 1;

    // SQL para criacao da tabela
    private static final String DATABASE_CREATE_TABLE_USUARIOS = "create table "
            + TABELA_USUARIO + "( " + COLUNA_ID_USUARIO + " integer primary key autoincrement, "
            + COLUNA_NOME_USUARIO + " text UNIQUE,"
            + COLUNA_SENHA_USUARIO + " text not null)";
    public NetFoodzSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_TABLE_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        // Log informando que a tabela está sendo reconstruida
        Log.w(NetFoodzSQLiteHelper.class.getName(),
                "Atualizando a base de dados da versao " + versaoAntiga
                        + " para " + novaVersao);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        onCreate(db);
    }

}