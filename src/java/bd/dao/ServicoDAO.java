package bd.dao;

import model.Servico;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServicoDAO {

    public ServicoDAO() {
    }
    
    public boolean salvar (Servico s, Conexao con)
    {
        String sql;
        Servico servico = new Servico();
        servico = getServico(s.getNome(),con);
        if (servico.getServico() == null) {
            sql = "insert into servico (serv_nome,serv_descricao,serv_valor,serv_quantidade) values ('"+s.getNome()+"','"+s.getDescricao()+"','"+s.getValor()+"',"+s.getQuantidade()+")";
            return con.manipular(sql);
        }
        return false;
    }

    public boolean alterar (Servico s, Conexao con)
    {   
        String sql = "update servico set serv_nome='"+s.getNome()+"', serv_descricao='"+s.getDescricao()+"', serv_valor="+s.getValor()+", serv_quantidade="+s.getQuantidade()+" where serv_id="+s.getId();
        return con.manipular(sql);  
    }

    public boolean excluir(int id, Conexao con)
    {
        return con.manipular("delete from servicos where serv_id=" + id);
    }
    
    public Servico getServico(int id, Conexao con)
    {   Servico s = new Servico();
        String sql="select * from servico where serv_id = " + id;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              s.setId(id);
              s.setNome(rs.getString("serv_nome"));
              s.setDescricao(rs.getString("serv_descricao"));
              s.setValor(rs.getString("serv_valor"));
              s.setQuantidade(rs.getBoolean("serv_quantidade"));
          }
        }
        catch(Exception e){System.out.println(e);}
        return s;
    }
    
    public Servico getServico(String nome, Conexao con)
    {   Servico s = new Servico();
        String sql="select * from servico where serv_nome = '"+nome +"'";
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              s.setId(rs.getInt("serv_id"));
              s.setNome(nome);
              s.setDescricao(rs.getString("serv_descricao"));
              s.setValor(rs.getString("serv_valor"));
              s.setQuantidade(rs.getBoolean("serv_quantidade"));
          }
        }
        catch(Exception e){System.out.println(e);}
        return s;
    }

    public ArrayList<Servico> getServicos(String filtro, Conexao con)
    {   
        ArrayList <Servico> lista = new ArrayList();
        String sql = "select * from servico";
        if (!filtro.isEmpty())
            sql+=" where " + filtro;
        sql += " order by serv_id";
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                lista.add(new Servico(
                    rs.getInt("serv_id"),
                    rs.getString("serv_nome"),
                    rs.getString("serv_descricao"),
                    rs.getDouble("serv_valor"),
                    rs.getInt("serv_quantidade")));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
}
