
package bd.dao;

import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;


public class NotificarDAO 
{
    public NotificarDAO(){}
    
    public boolean salvar (int id, String msg, Conexao con)
    {
        String sql="insert into notificacoes (notif_usu, notif_desc) values " + "("+id+",'"+msg+"')";
        return con.manipular(sql);
    }
    public ArrayList<String> getContas(String filtro, Conexao con)
    {   
        ArrayList <String> lista = new ArrayList();
        String sql = "select * from notificacoes";
        if (!filtro.isEmpty())
            sql+=" where " + filtro;
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                lista.add(rs.getString("notif_desc"));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
}
