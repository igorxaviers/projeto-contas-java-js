package bd.dao;

import model.Usuario;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {

    public UsuarioDAO() {
    }
    
    public boolean salvar (Usuario u, Conexao con)
    {
        String sql;
        Usuario usuario = new Usuario();
        usuario = getUsuarioPorLogin(u.getLogin(), con);
        if (usuario.getLogin() == null){
            sql = "insert into usuarios (usu_nome,usu_login,usu_senha,usu_admin,usu_ativo) values ('"+u.getNome()+"','"+u.getLogin()+"','"+u.getSenha()+"',"+u.isAdmin()+","+u.isAtivo()+")";
            return con.manipular(sql);
        }
        return false;
    }

    public boolean alterar (Usuario u, Conexao con)
    {   
        String sql = "update usuarios set usu_nome='"+u.getNome()+"', usu_login='"+u.getLogin()+"', usu_senha='"+u.getSenha()+"', usu_admin="+u.isAdmin()+", usu_ativo="+u.isAtivo()+" where usu_id="+u.getId();
        return con.manipular(sql);                 
    }

    public boolean excluir(int id, Conexao con)
    {
        return con.manipular("delete from usuarios where usu_id=" + id);
    }

    public Usuario getUsuario(int id, Conexao con)
    {   Usuario u = new Usuario();
        String sql="select * from usuarios where usu_id = " + id;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              u.setId(id);
              u.setNome(rs.getString("usu_nome"));
              u.setLogin(rs.getString("usu_login"));
              u.setSenha(rs.getString("usu_senha"));
              u.setAdmin(rs.getBoolean("usu_admin"));
              u.setAtivo(rs.getBoolean("usu_ativo"));
          }

        }
        catch(Exception e){System.out.println(e);}
        return u;
    }

    public ArrayList<Usuario> getUsuarios(String filtro, Conexao con)
    {   
        ArrayList <Usuario> lista = new ArrayList();
        String sql = "select * from usuarios";
        if (!filtro.isEmpty())
            sql+=" where " + filtro;
        sql += " order by usu_id";
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                lista.add(new Usuario(
                    rs.getInt("usu_id"),
                    rs.getString("usu_nome"),
                    rs.getString("usu_login"),
                    rs.getString("usu_senha"),
                    rs.getBoolean("usu_admin"),
                    rs.getBoolean("usu_ativo")));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }

    public Usuario getUsuarioPorLogin(String login, Conexao con)
    {   
        Usuario u = new Usuario();
        String sql="select * from usuarios where upper(usu_login) = '"+login.toUpperCase()+"'";
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
            u.setId(rs.getInt("usu_id"));
            u.setNome(rs.getString("usu_nome"));
            u.setLogin(rs.getString("usu_login"));
            u.setSenha(rs.getString("usu_senha"));
            u.setAdmin(rs.getBoolean("usu_admin"));
            u.setAtivo(rs.getBoolean("usu_ativo"));
            return u;
          }
        }
        catch(Exception e){System.out.println(e);}
        return null;
    }

    public ArrayList<String> getNotificacoes(int id, Conexao con)
    {
        ArrayList<String> notificacoes = new ArrayList<>();
        String sql = "select * from notificacoes where notif_usu =" + id;
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                notificacoes.add(rs.getString("notif_desc"));
        }catch(Exception e){}
        return notificacoes;
    }

    public Usuario validaLogin(String login, String senha, Conexao con)
    {
        Usuario u = new Usuario();
        String sql="select * from usuarios where usu_login = '"+login+"' AND usu_senha = '"+senha+"'";
        ResultSet rs = con.consultar(sql);
        try
        {
            if (rs.next())
            {
                u.setId(rs.getInt("usu_id"));
                u.setNome(rs.getString("usu_nome"));
                u.setLogin(rs.getString("usu_login"));
                u.setSenha(rs.getString("usu_senha"));
                u.setAdmin(rs.getBoolean("usu_admin"));
                u.setAtivo(rs.getBoolean("usu_ativo"));
            }
            return u;
        }
        catch(Exception e){System.out.println(e);}
        return null;
    }
}
