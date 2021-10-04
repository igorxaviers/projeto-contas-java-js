package bd.dao;

import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Fornecedor;

public class FornecedorDAO {
    public FornecedorDAO() {
    }
    
    public boolean salvar (Fornecedor f, Conexao con)
    {
        String sql;
        Fornecedor fornecedor = new Fornecedor();
        fornecedor = getFornecedorPorRazao(f.getRazao(), con);
        if (fornecedor.getRazao() == null){
            sql = "insert into fornecedores (forn_cnpj, forn_razao, forn_fantasia, forn_endereco, forn_bairro, forn_email, forn_inscricao_estadual, forn_cep, cat_id_categoria, forn_cidade) values ('"+f.getCnpj()+"','"+f.getRazao()+"','"+f.getFantasia()+"','"+f.getEndereco()+"','"+f.getBairro()+"','"+f.getEmail()+"','"+f.getInscricao_estadual()+"','"+f.getCep()+"',"+f.getCategoria().getId()+",'"+f.getCidade()+"')";
            return con.manipular(sql);
        }
        return false;
    }
    
    public boolean alterar (Fornecedor f, Conexao con)
    {   
        String sql = "update fornecedores set forn_razao='"+f.getRazao()+"', forn_fantasia='"+f.getFantasia()+"', forn_endereco='"+f.getEndereco()+"', forn_bairro='"+f.getBairro()+"', forn_email='"+f.getEmail()+"', forn_inscricao_estadual='"+f.getInscricao_estadual()+"', forn_cep='"+f.getCep()+"', cat_id_categoria='"+f.getCategoria().getId()+"', forn_cidade='"+f.getCidade()+"' where forn_cnpj='"+f.getCnpj()+"'";
        return con.manipular(sql);                 
    }

    public boolean excluir(String cnpj, Conexao con)
    {
        return con.manipular("delete from fornecedores where forn_cnpj='" + cnpj + "'");
    }

    public Fornecedor getFornecedor(String cnpj, Conexao con)
    {   Fornecedor f = new Fornecedor();
        String sql="select * from fornecedores where forn_cnpj = '" + cnpj + "'";
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              f.setCnpj(cnpj);
              f.setRazao(rs.getString("forn_razao"));
              f.setFantasia(rs.getString("forn_fantasia"));
              f.setEndereco(rs.getString("forn_endereco"));
              f.setBairro(rs.getString("forn_bairro"));
              f.setEmail(rs.getString("forn_email"));
              f.setInscricao_estadual(rs.getString("forn_inscricao_estadual"));
              f.setCep(rs.getString("forn_cep"));
              f.setCategoria(new CategoriaDAO().getCategoria(rs.getInt("cat_id_categoria"), con));
              f.setCidade(rs.getString("forn_cidade"));
          }

        }
        catch(Exception e){System.out.println(e);}
        return f;
    }

    public ArrayList<Fornecedor> getFornecedores(String filtro, Conexao con)
    {   
        ArrayList <Fornecedor> lista = new ArrayList();
        String sql = "select * from fornecedores";
        if (!filtro.isEmpty())
            sql+=" where " + filtro;
        sql += " order by forn_cnpj";
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                lista.add(new Fornecedor(
                    rs.getString("forn_cnpj"),
                    rs.getString("forn_razao"),
                    rs.getString("forn_fantasia"),
                    rs.getString("forn_endereco"),
                    rs.getString("forn_bairro"),
                    rs.getString("forn_email"),
                    rs.getString("forn_inscricao_estadual"),
                    rs.getString("forn_cep"),
                    rs.getString("forn_cidade"),
                    new CategoriaDAO().getCategoria(rs.getInt("cat_id_categoria"), con)));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }

    public Fornecedor getFornecedorPorRazao(String nome, Conexao con)
    {   Fornecedor f = new Fornecedor();
        String sql="select * from fornecedores where forn_razao = '"+nome+"'";
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              f.setCnpj(rs.getString("forn_cnpj"));
              f.setRazao(rs.getString("forn_razao"));
              f.setFantasia(rs.getString("forn_fantasia"));
              f.setEndereco(rs.getString("forn_endereco"));
              f.setBairro(rs.getString("forn_bairro"));
              f.setEmail(rs.getString("forn_email"));
              f.setInscricao_estadual(rs.getString("forn_inscricao_estadual"));
              f.setCep(rs.getString("forn_cep"));
              f.setCategoria(new CategoriaDAO().getCategoria(rs.getInt("cat_id_categoria"), con));
              f.setCidade(rs.getString("forn_cidade"));
          }
        }
        catch(Exception e){System.out.println(e);}
        return f;
    }
}
