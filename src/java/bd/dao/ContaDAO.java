
package bd.dao;

import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Conta;
import model.Fornecedor;
import model.Usuario;


public class ContaDAO 
{

    public ContaDAO()
    {
    }
    
    public boolean salvar (Conta c, Conexao con)
    {
        String sql="insert into contas (cont_tipo,cont_data,cont_data_vencimento,cont_descricao,cont_status,cont_valor,usu_id_usuarios,forn_cnpj) values "
                + "("+c.getTipo()+",'"+c.getData()+"','"+c.getData_vencimento()+"','"+c.getDescricao()+"','"+c.getStatus().getNome()+"',"+c.getValor()+","+c.getUsuario().getId()+", '"+c.getFornecedor().getCnpj()+"')";
        return con.manipular(sql);
    }
    
    public boolean alterar (Conta c, Conexao con)
    {   
        String sql = "update contas set cont_status='"+c.getStatus().getNome()+"', cont_valor ="+c.getValor()+",cont_data='"+c.getData()+"',cont_data_vencimento='"+c.getData_vencimento()+"',cont_descricao='"+c.getDescricao()+"', forn_cnpj='"+c.getFornecedor().getCnpj()+"' where cont_id="+c.getId();
        return con.manipular(sql);                 
    }

    public boolean excluir(int id, Conexao con)
    {
        return con.manipular("delete from contas where cont_id=" + id);
    }

    public Conta getConta(int id, Conexao con)
    {   Conta c = new Conta();
        String sql="select * from contas where cont_id = " + id;
        String aux;
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
                c.setUsuario(new Usuario(rs.getInt("usu_id_usuarios")).getUsuario(con));
                c.setValor(rs.getDouble("cont_valor"));
                aux = rs.getString("cont_status");
                c.setStatus(c.valida(aux));
                c.setFornecedor(new Fornecedor(rs.getString("forn_cnpj")).getFornecedor(con));
            }
        }
        catch(Exception e){System.out.println(e);}
        return c;
    }

    public ArrayList<Conta> getContas(String filtro, Conexao con)
    {   
        Conta c = new Conta();
        ArrayList <Conta> lista = new ArrayList<>();
        String aux;
        String sql = "select * from contas";
        if (!filtro.isEmpty())
            sql+=" where " + filtro;
        sql += " order by cont_id";
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
            {
                aux = rs.getString("cont_status");
                lista.add(
                    new Conta(
                        rs.getInt("cont_id"),
                        rs.getInt("cont_tipo"),
                        rs.getString("cont_descricao"),
                        rs.getDate("cont_data"),
                        rs.getDate("cont_data_vencimento"),
                        rs.getDouble("cont_valor"),
                        new Usuario(rs.getInt("usu_id_usuarios")).getUsuario(con),
                        c.valida(aux),
                        new Fornecedor(rs.getString("forn_cnpj")).getFornecedor(con))
                    );
            }
                
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }

    public ArrayList<Conta> getContasLimit(int limit, Conexao con)
    {   
        Conta c = new Conta();
        ArrayList <Conta> lista = new ArrayList<>();
        String aux;
        String sql = "select * from contas order by cont_id limit "+ limit;
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
            {
                aux = rs.getString("cont_status");
                lista.add(
                    new Conta(
                        rs.getInt("cont_id"),
                        rs.getInt("cont_tipo"),
                        rs.getString("cont_descricao"),
                        rs.getDate("cont_data"),
                        rs.getDate("cont_data_vencimento"),
                        rs.getDouble("cont_valor"),
                        new Usuario(rs.getInt("usu_id_usuarios")).getUsuario(con),
                        c.valida(aux),
                        new Fornecedor(rs.getString("forn_cnpj")).getFornecedor(con))
                    );
            }
                
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }

    public int countContas(int tipo, Conexao con)
    {
        String sql ="SELECT COUNT(cont_tipo) FROM contas WHERE cont_tipo = "+tipo;
        ResultSet rs = con.consultar(sql);
        int count = 0;
        try
        {
            if(rs.next())
                count = rs.getInt("count");

        }catch(Exception e){System.out.println(e);}
        return count;
    }

    public double somaValorContas(int tipo, Conexao con)
    {
        String sql ="SELECT SUM(cont_valor) AS soma FROM contas WHERE cont_tipo = "+ tipo;
        ResultSet rs = con.consultar(sql);
        double soma = 0;
        try
        {
            if(rs.next())
                soma = rs.getInt("soma");

        }catch(Exception e){System.out.println(e);}
        return soma;
    }
    
}
