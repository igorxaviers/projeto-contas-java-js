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
        return new CategoriaDAO().salvar(this, con);
    }

    public boolean alterar(Conexao con) {
        return new CategoriaDAO().alterar(this, con);
    }

    public boolean excluir(Conexao con) {
        return new CategoriaDAO().excluir(this.id, con);
    }

    public Categoria getCategoria(Conexao con) {
        return new CategoriaDAO().getCategoria(this.id, con);
    }
    
    public Categoria getCategoriaPorNome(Conexao con) {
        return new CategoriaDAO().getCategoriaPorNome(this.nome, con);
    }
    public ArrayList<Categoria> getCategorias(String filtro, Conexao con) {
        return new CategoriaDAO().getCategorias(filtro, con);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }    

    public boolean valida() {
        if(!nome.isEmpty() && nome.length()>3)
            return true;
        return false;
    }
    
}