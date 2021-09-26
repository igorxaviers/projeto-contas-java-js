package model;

import bd.dao.EstadoDAO;
import bd.util.Conexao;
import java.util.ArrayList;

public class Estado {
    private int id;
    private String nome;

    public Estado(){}
 
    public Estado(int id) {
        this.id = id;
    }

    public Estado(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Estado(String nome) {
        this.nome = nome;
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

    /* ============== OPERACOES BD ============== */

    public boolean salvar(Conexao con) {
        EstadoDAO eDAO = new EstadoDAO();
        return eDAO.salvar(this, con);
    }

    public boolean alterar(Conexao con) {
        EstadoDAO eDAO = new EstadoDAO();
        return eDAO.alterar(this, con);
    }

    public boolean excluir(Conexao con) {
        EstadoDAO eDAO = new EstadoDAO();
        return eDAO.excluir(this.id, con);
    }

    public Estado getEstado(Conexao con) {
        EstadoDAO eDAO = new EstadoDAO();
        return eDAO.getEstado(this.id, con);
    }
    public ArrayList<Estado> getEstados(String filtro, Conexao con) {
        EstadoDAO eDAO = new EstadoDAO();
        return eDAO.getEstados(filtro, con);
    }

    @Override
    public String toString() {
        return "{" +
            " id= '" + getId() + "'" +
            ", nome= '" + getNome() + "'" +"}";
    } 
}

