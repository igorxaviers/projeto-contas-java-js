package bd.dao;

import model.Categoria;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDAO {

    public CategoriaDAO() {
    }
    
    public boolean salvar (Categoria c, Conexao con)
    {
        String sql;
        Categoria categoria = new Categoria();
        categoria = getCategoriaPorNome(c.getNome(), con);
        if (categoria.getNome() == null){
            sql = "insert into categorias (cat_nome) values ('"+c.getNome()+"')";
            return con.manipular(sql);
        }
        return false;
    }

    public boolean alterar (Categoria c, Conexao con)
    {   
        String sql;
        Categoria categoria = new Categoria();
        categoria = getCategoriaPorNome(c.getNome(), con);
        if (categoria.getNome() == null || c.getId() == categoria.getId()){
            sql = "update categorias set cat_nome='"+c.getNome()+"' where cat_id="+c.getId();
            return con.manipular(sql);                 
        }
        return false;
    }

    public boolean excluir(int id, Conexao con)
    {
        return con.manipular("delete from categorias where cat_id=" + id);
    }

    public Categoria getCategoria(int id, Conexao con)
    {   Categoria c = new Categoria();
        String sql="select * from categorias where cat_id = " + id;
        ResultSet rs = con.consultar(sql);
        try
        {
            if (rs.next())
            {
                c.setId(id);
                c.setNome(rs.getString("cat_nome"));
            }
        }
        catch(Exception e){System.out.println(e);}
        return c;
    }

    public ArrayList<Categoria> getCategorias(String filtro, Conexao con)
    {   
        ArrayList <Categoria> lista = new ArrayList();
        String sql = "select * from categorias";
        if (!filtro.isEmpty())
            sql+=" where " + filtro;
        sql += " order by cat_id";
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                lista.add(new Categoria(
                    rs.getInt("cat_id"),
                    rs.getString("cat_nome")));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }

    public Categoria getCategoriaPorNome(String nome, Conexao con)
    {   Categoria c = new Categoria();
        String sql="select * from categorias where cat_nome = '"+nome+"'";
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
            c.setId(rs.getInt("cat_id"));
            c.setNome(rs.getString("cat_nome"));
          }
        }
        catch(Exception e){System.out.println(e);}
        return c;
    }
}