package bd.dao;

import model.Cidade;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Estado;

public class CidadeDAO {

    private boolean add;

    public CidadeDAO(){
    }
    
    public boolean salvar (Cidade c, Conexao con)
    {
        String sql;
        Cidade cidade = new Cidade();
        cidade = getCidadePorNome(c.getNome(),con);
        if (cidade.getNome() == null) {
            sql = "insert into cidade (cid_nome,est_id_estado) values ('"+c.getNome()+"','"+c.getEstado()+"')";
            return con.manipular(sql);
        }
        return false;
    }

    public boolean alterar (Cidade c, Conexao con)
    {   
        String sql = "update cidade set scid_nome='"+c.getNome()+"', est_id_estado='"+c.getEstado();
        return con.manipular(sql);  
    }

    public boolean excluir(int id, Conexao con)
    {
        return con.manipular("delete from cidade where cid_id=" + id);
    }
    
    public Cidade getCidade(int id, Conexao con)
    {   Cidade c = new Cidade();
        String sql="select * from cidade where cid_id = " + id;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              c.setId(id);
              c.setNome(rs.getString("cid_nome"));
              c.getEstado().setId((rs.getInt("est_id_estado")));
          }
        }
        catch(Exception e){System.out.println(e);}
        return c;
    }
    
    public Cidade getCidadePorNome(String nome, Conexao con)
    {   Cidade c = new Cidade();
        String sql="select * from cidade where cid_nome = '"+nome +"'";
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              c.setId(rs.getInt("cid_id"));
              c.setNome(nome);
              c.getEstado().setId(rs.getInt("est_id_estado"));
          }
        }
        catch(Exception e){System.out.println(e);}
        return c;
    }

    public ArrayList<Cidade> getCidades(String filtro, Conexao con)
    {   
        ArrayList <Cidade> lista = new ArrayList();
        String sql = "select * from cidade";
        if (!filtro.isEmpty())
            sql+=" where " + filtro;
        sql += " order by cid_id";
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                add = lista.add(new Cidade(
                    rs.getInt("cid_id"),
                    rs.getString("cid_nome"),
                    new Estado(rs.getInt("est_id_estado"))));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
}
