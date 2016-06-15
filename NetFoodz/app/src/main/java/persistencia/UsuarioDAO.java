package persistencia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dominio.Usuario;

public class UsuarioDAO {

    HttpResponse<JsonNode> resposta = Unirest.post(urlLogin)
            .headers(headers)
            .fields(fields)
            .asJson();

    public UsuarioDAO(Context context) {
        dbHelper = new NetFoodzSQLiteHelper(context);
    }

    // Inicia conexao com o banco de dados
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Finaliza a conexao com o banco de dados
    public void conectar() {
        HttpResponse<JsonNode> resposta = Unirest.post(urlLogin)
                .headers(headers)
                .fields(fields)
                .asJson();
        JSONObject body = resposta.getBody().getObject();
        Headers headerBody = resposta.getHeaders();
        String tmp = headerBody.get("set-cookie").get(0);
        this.cookie = "sd-touch-mode=false; ".concat(tmp.replace(";Path=/;HttpOnly",""));
        Map<String,String> cabeceras = new HashMap<String, String>();
        cabecera.put("Cookie", this.cookie);
        HttpResponse<JsonNode> respuesta = Unirest.post(urlFixpack)
                .headers(headers)
                .fields(fields)
                .asJson();

    }

    // Metodo para criacao de usuario
    public Usuario criarUsuario(String nome, String senha) {

        ContentValues values = new ContentValues();
        values.put(NetFoodzSQLiteHelper.COLUNA_NOME_USUARIO, nome);
        values.put(NetFoodzSQLiteHelper.COLUNA_SENHA_USUARIO, senha);

        long insertId = database.insert(NetFoodzSQLiteHelper.TABELA_USUARIO,
                null, values);

        Cursor cursor = database.query(NetFoodzSQLiteHelper.TABELA_USUARIO,
                colunas, NetFoodzSQLiteHelper.COLUNA_ID_USUARIO + " = " + insertId,
                null, null, null, null);
        cursor.moveToFirst();

        Usuario usuario = montaUsuario(cursor);
        cursor.close();

        return usuario;
    }

    // Metodo para deletar usuario
    public void deletarUsuario(Usuario usuario) {
        String login = usuario.getLogin();
        System.out.println("Usuario deletado: " + login);
        database.delete(NetFoodzSQLiteHelper.TABELA_USUARIO,
                NetFoodzSQLiteHelper.COLUNA_ID_USUARIO + " = " + usuario.getId(), null);
    }

    // MÃ©todo para autenticar login e senha
    public boolean verificaUsuario(Usuario user) {
        // Cria um cursor na tabela de usuarios
        Cursor cursor = database.query(NetFoodzSQLiteHelper.TABELA_USUARIO,
                colunas, null, null, null, null, null);
        boolean result = false;
        // Move o cursor para a primeira linha da tabela
        cursor.moveToFirst();
        // laÃ§o que dura atÃ© que todos os usuÃ¡rios sejam verificados ou que seja
        // encontrado o usuÃ¡rio informado
        while (!cursor.isAfterLast()) {
            Usuario usuario = new Usuario();
            usuario = montaUsuario(cursor);
            if (user.getLogin().equals(usuario.getLogin())
                    && user.getSenha().equals(usuario.getSenha())) {
                return true;
            } else {
                cursor.moveToNext();
            }
        }
        return result;
    }

//	// Metodo para listar todos usuarios no banco de dados
//	public List<Usuario> listarUsuarios() {
//		List<Usuario> usuarios = new ArrayList<Usuario>();
//
//		Cursor cursor = database.query(HortaSQLiteHelper.TABELA_USUARIO,
//				colunas, null, null, null, null, null);
//
//		cursor.moveToFirst();
//		while (!cursor.isAfterLast()) {
//			Usuario usuario = montaUsuario(cursor);
//			usuarios.add(usuario);
//			cursor.moveToNext();
//		}
//
//		// Finaliza o cursor
//		cursor.close();
//		return usuarios;
//	}

    // Recupera um usuario a partir do cursor de listagem
    private Usuario montaUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getLong(0));
        usuario.setLogin(cursor.getString(1));
        usuario.setSenha(cursor.getString(2));
        return usuario;

    }
    public Cursor getAllTitles()
    {
        return database.query(NetFoodzSQLiteHelper.TABELA_USUARIO, new String[] {
                        "usuario",
                        "senha",
                        "_id"},
                null,
                null,
                null,
                null,
                null);
    }



}