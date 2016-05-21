package services;

import java.util.List;
import dominio.Usuario;
import persistencia.UsuarioDAO;
import android.app.Activity;
import android.content.Context;

public class UsuarioServices extends Activity{

    private static UsuarioDAO usuarioDAO;
    private List<Usuario> lista;
    private static UsuarioServices instance;

    public UsuarioServices(Context context) {
        usuarioDAO = new UsuarioDAO(context);
    }

    public void insertuser(Context context, String nome, String senha) {
        usuarioDAO.criarUsuario(nome, senha);
    }

    public void ligaBanco(Context context) {
        usuarioDAO.open();

    }
    public Object getItem(int position) {
        return lista.get(position);
    }


    public static UsuarioServices getInstance(Context context) {
        if (instance == null) {
            instance = new UsuarioServices(null);
            usuarioDAO = new UsuarioDAO(context);
        }
        return instance;
    }

    public boolean verificauser(Context context, String login, String senha) {
        Usuario user = new Usuario();
        user.setLogin(login);
        user.setSenha(senha);
        boolean result = usuarioDAO.verificaUsuario(user);
        return result;
    }
}
