package bd.dao;

import model.Item;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDAO {

    public ItemDAO() {
    }
    
    public boolean salvar (Item i, Conexao con)
    {
        String sql;
        Item item = new Item();
        item = getItemPorNome(i.getNome(),con);
        if (item.getNome() == null) {
            sql = "insert into item (item_nome,item_descricao,item_valor,item_quantidade) values ('"+i.getNome()+"','"+i.getDescricao()+"','"+i.getValor()+"',"+i.getQuantidade()+")";
            return con.manipular(sql);
        }
        return false;
    }

    public boolean alterar (Item i, Conexao con)
    {   
        String sql = "update item set item_nome='"+i.getNome()+"', item_descricao='"+i.getDescricao()+"', item_valor="+i.getValor()+", item_quantidade="+i.getQuantidade()+" where item_id="+i.getId();
        return con.manipular(sql);  
    }

    public boolean excluir(int id, Conexao con)
    {
        return con.manipular("delete from item where item_id=" + id);
    }
    
    public Item getItem(int id, Conexao con)
    {   Item i = new Item();
        String sql="select * from item where item_id = " + id;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              i.setId(id);
              i.setNome(rs.getString("item_nome"));
              i.setDescricao(rs.getString("item_descricao"));
              i.setValor(rs.getDouble("item_valor"));
              i.setQuantidade(rs.getInt("item_quantidade"));
          }
        }
        catch(Exception e){System.out.println(e);}
        return i;
    }
    
    public Item getItemPorNome(String nome, Conexao con)
    {   Item i = new Item();
        String sql="select * from item where item_nome = '"+nome +"'";
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              i.setId(rs.getInt("item_id"));
              i.setNome(nome);
              i.setDescricao(rs.getString("item_descricao"));
              i.setValor(rs.getDouble("item_valor"));
              i.setQuantidade(rs.getInt("item_quantidade"));
          }
        }
        catch(Exception e){System.out.println(e);}
        return i;
    }

    public ArrayList<Item> getItens(String filtro, Conexao con)
    {   
        ArrayList <Item> lista = new ArrayList();
        String sql = "select * from item";
        if (!filtro.isEmpty())
            sql+=" where " + filtro;
        sql += " order by item_id";
        ResultSet rs = con.consultar(sql);
        try
        {
            while(rs.next())
                lista.add(new Item(
                    rs.getInt("item_id"),
                    rs.getString("item_nome"),
                    rs.getString("item_descricao"),
                    rs.getDouble("item_valor"),
                    rs.getInt("item_quantidade")));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
}

