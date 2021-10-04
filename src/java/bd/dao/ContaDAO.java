
package bd.dao;

import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Conta;


public class ContaDAO 
{

    public ContaDAO()
    {
    }
    
    public boolean salvar (Conta c, Conexao con)
    {
        String sql="insert into contas (cont_tipo,cont_data,cont_data_vencimento,cont_descricao,cont_aprovado,cont_valor,usu_id_usuarios) values "
                + "("+c.getTipo()+",'"+c.getData()+"','"+c.getData_vencimento()+"','"+c.getDescricao()+"',"+c.isAprovada()+","+c.getValor()+","+c.getUsuario().getId();
        return con.manipular(sql);
    }
    
    public boolean alterar (Conta c, Conexao con)
    {   
        String sql = "update contas set cont_aprovado="+c.isAprovada()+" where cont_id="+c.getId();
        return con.manipular(sql);                 
    }

    public boolean excluir(int id, Conexao con)
    {
        return con.manipular("delete from contas where cont_id=" + id);
    }

    public Conta getConta(int id, Conexao con)
    {   Conta c = new Conta();
        String sql="select * from contas where cont_id = " + id;
        ResultSet rs = con.consultar(sql);
        try
        {
            if (rs.next())
            {
                c.setId(id);
                c.setData(rs.getDate("cont_data"));
                c.setData_vencimento(rs.getDate("cont_data_vencimento"));
                c.setDescricao(rs.getString("cont_descricao"));
                c.setTipo(rs.getInt("cont_tipo"));
                c.setUsuario(new UsuarioDAO().getUsuario(rs.getInt("usu_id_usuarios"), con));
                c.setValor(rs.getDouble("cont_valor"));
                c.setAprovada(rs.getBoolean("cont_aprovado"));
                        
            }
        }
        catch(Exception e){System.out.println(e);}
        return c;
    }

    public ArrayList<Conta> getContas(String filtro, Conexao con)
    {   
        ArrayList <Conta> lista = new ArrayList();
        String sql = "select * from contas";
        if (!filtro.isEmpty())
            sql+=" where " + filtro;
        sql += " order by cat_id";
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                lista.add(new Conta(
                    rs.getInt("cont_id"),
                    rs.getInt("cont_tipo"),
                    rs.getString("cont_descricao"),
                    rs.getDate("cont_data"),
                    rs.getDate("cont_data_vencimento"),
                    rs.getDouble("cont_valor"),
                    new UsuarioDAO().getUsuario(rs.getInt("usu_id_usuarios"),con),
                    rs.getBoolean("cont_aprovada")));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }

    
}
