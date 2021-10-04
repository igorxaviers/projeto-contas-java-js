package model;

import bd.dao.CategoriaDAO;
import bd.util.Conexao;
import java.util.ArrayList;

public class Categoria {
    private int id;
    private String nome;

    public Categoria(){}

    public Categoria(String nome) {
        this.nome = nome;
    }
    public Categoria(int id)
    {
        this.id=id;
    }
    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /* ============== OPERAÇÕES BD ============== */

    public boolean salvar(Conexao con) {
        CategoriaDAO cDAO = new CategoriaDAO();
        return cDAO.salvar(this, con);
    }

    public boolean alterar(Conexao con) {
        CategoriaDAO cDAO = new CategoriaDAO();
        return cDAO.alterar(this, con);
    }

    public boolean excluir(Conexao con) {
        CategoriaDAO cDAO = new CategoriaDAO();
        return cDAO.excluir(this.id, con);
    }

    public Categoria getCategoria(Conexao con) {
        CategoriaDAO cDAO = new CategoriaDAO();
        return cDAO.getCategoria(this.id, con);
    }
    
    public Categoria getCategoriaPorNome(Conexao con) {
        CategoriaDAO cDAO = new CategoriaDAO();
        return cDAO.getCategoriaPorNome(this.nome, con);
    }
    public ArrayList<Categoria> getCategorias(String filtro, Conexao con) {
        CategoriaDAO cDAO = new CategoriaDAO();
        return cDAO.getCategorias(filtro, con);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }    
    
}