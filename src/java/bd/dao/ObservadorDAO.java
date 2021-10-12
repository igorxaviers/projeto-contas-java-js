package bd.dao;

import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Observer;
import model.Usuario;

public class ObservadorDAO {
    public ObservadorDAO() {
    }
    
    public boolean adicionar (int usuario, int conta, Conexao con)
    {
        String sql;
        boolean achou = false;
        achou = getObservador(usuario, conta , con);
        if (!achou){
            sql = "insert into observadores (id_usu, id_conta) values ("+usuario+","+conta+")";
            return con.manipular(sql);
        }
        return false;
    }
    
    public boolean excluir(int usuario, int conta, Conexao con)
    {
        return con.manipular("delete from observadores where id_usu=" + usuario + " and id_conta = "+ conta);
    }
    
    public boolean excluirCascata(int conta, Conexao con)
    {
        return con.manipular("delete from observadores where id_conta = "+ conta);
    }
        
    public boolean getObservador(int usuario, int conta, Conexao con)
    {   
        String sql="select * from observadores where id_usu = " + usuario + " and id_conta = " + conta;
        ResultSet rs = con.consultar(sql);
        try
        {
            if (rs.next())
                return true;
        }
        catch(Exception e){System.out.println(e);}
        return false;
    }
    
    public ArrayList<Observer> getObservadores(int conta, Conexao con)
    {   
        ArrayList <Observer> lista = new ArrayList<>();
        String sql = "select * from observadores where id_conta = " + conta;
        sql += " order by id_usu";
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                lista.add(new UsuarioDAO().getUsuario(rs.getInt("id_usu"), con));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    public ArrayList<Observer> addTodosAdmin(int id_conta, Conexao con)
    {   
        ArrayList <Observer> lista = new ArrayList<>();
        String sql = "select * from usuarios where usu_admin = true and usu_ativo = true";
        sql += " order by usu_id";
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
            {
                Usuario usu = new UsuarioDAO().getUsuario(rs.getInt("usu_id"), con);
                lista.add(usu);
                adicionar(usu.getId(),id_conta,con);
            }
                
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    
    
    
    
    
    


}
