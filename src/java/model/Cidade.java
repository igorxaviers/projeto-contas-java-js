package model;

import bd.dao.CidadeDAO;
import bd.util.Conexao;
import java.util.ArrayList;

public class Cidade {
    private int id;
    private String nome;
    Estado estado;

    public Cidade(){}
 
    public Cidade(int id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
    
    public Cidade(String nome, String descricao, Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /* ============== OPERACOES BD ============== */

    public boolean salvar(Conexao con) {
        CidadeDAO cDAO = new CidadeDAO();
        return cDAO.salvar(this, con);
    }

    public boolean alterar(Conexao con) {
        CidadeDAO cDAO = new CidadeDAO();
        return cDAO.alterar(this, con);
    }

    public boolean excluir(Conexao con) {
        CidadeDAO cDAO = new CidadeDAO();
        return cDAO.excluir(this.id, con);
    }

    public Cidade getServico(Conexao con) {
        CidadeDAO cDAO = new CidadeDAO();
        return cDAO.getCidade(this.id, con);
    }
    public ArrayList<Cidade> getCidades(String filtro, Conexao con) {
        CidadeDAO cDAO = new CidadeDAO();
        return cDAO.getCidades(filtro, con);
    }

    @Override
    public String toString() {
        return "{" +
            " id= '" + getId() + "'" +
            ", nome= '" + getNome() + "'" +
            ", Estado= '" + getEstado().getNome() + "'" +"}";
    } 
}
