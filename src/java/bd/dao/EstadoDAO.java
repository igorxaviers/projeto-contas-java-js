package bd.dao;

import model.Estado;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EstadoDAO {

    public EstadoDAO() {
    }

    public boolean salvar(Estado e, Conexao con) {
        String sql;
        Estado estado = new Estado();
        estado = getEstado(e.getNome(), con);
        if (estado.getEstado(con) == null) {
            sql = "insert into estados (est_nome) values ('" + e.getNome() + "')";
            return con.manipular(sql);
        }
        return false;
    }

    public boolean alterar(Estado e, Conexao con) {
        String sql = "update estados set est_nome='" + e.getNome() + "' where est_id=" + e.getId();
        return con.manipular(sql);
    }

    public boolean excluir(int id, Conexao con) {
        return con.manipular("delete from estados where est_id=" + id);
    }

    public Estado getEstado(int id, Conexao con) {
        Estado e = new Estado();
        String sql = "select * from estados where est_id = " + id;
        ResultSet rs = con.consultar(sql);
        try {
            if (rs.next()) {
                e.setId(id);
                e.setNome(rs.getString("est_nome"));
            }
        } catch (Exception er) {
            System.out.println(er);
        }
        return e;
    }

    public Estado getEstado(String nome, Conexao con) {
        Estado e = new Estado();
        String sql = "select * from estados where est_nome = '" + nome + "'";
        ResultSet rs = con.consultar(sql);
        try {
            if (rs.next()) {
                e.setId(rs.getInt("est_id"));
                e.setNome(nome);
            }
        } catch (Exception er) {
            System.out.println(er);
        }
        return e;
    }

    public ArrayList<Estado> getEstados(String filtro, Conexao con) {
        ArrayList<Estado> lista = new ArrayList();
        String sql = "select * from estados";
        if (!filtro.isEmpty())
            sql += " where " + filtro;
        sql += " order by est_id";
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                lista.add(new Estado(rs.getInt("est_id"), rs.getString("est_nome")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
}
