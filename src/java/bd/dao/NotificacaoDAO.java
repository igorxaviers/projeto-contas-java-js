
package bd.dao;

import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.Notificacao;
import model.Usuario;


public class NotificacaoDAO 
{
    public NotificacaoDAO(){}
    
    public boolean salvar (int id, String msg, Conexao con)
    {
        Date data_atual = new Date();
        String sql="insert into notificacoes (notif_usu, notif_desc, notif_data) values " + "("+id+",'"+msg+"', '"+data_atual+"' )";
        return con.manipular(sql);
    }

    public ArrayList<Notificacao> getNotificacoes(int id, Conexao con)
    {
        ArrayList<Notificacao> notificacoes = new ArrayList<>();
        String sql = "select * from notificacoes where notif_usu =" + id;
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                notificacoes.add(new Notificacao(new Usuario(rs.getInt("notif_usu")), rs.getString("notif_desc"), rs.getDate("notif_data")));

        }catch(Exception e){}
        return notificacoes;
    }

}
