package model;

import bd.dao.ItemDAO;
import bd.util.Conexao;
import java.util.ArrayList;

public class Item {
    private int id, quantidade;
    private String nome, descricao;
    private double valor;

    public Item(){}
 
    public Item(int id, String nome, String descricao,double valor, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }
    
    public Item(String nome, String descricao,double valor, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    /* ============== OPERACOES BD ============== */

    public boolean salvar(Conexao con) {
        ItemDAO iDAO = new ItemDAO();
        return iDAO.salvar(this, con);
    }

    public boolean alterar(Conexao con) {
        ItemDAO iDAO = new ItemDAO();
        return iDAO.alterar(this, con);
    }

    public boolean excluir(Conexao con) {
        ItemDAO iDAO = new ItemDAO();
        return iDAO.excluir(this.id, con);
    }

    public Item getItem(Conexao con) {
        ItemDAO iDAO = new ItemDAO();
        return iDAO.getItem(this.id, con);
    }
    public ArrayList<Item> getItens(String filtro, Conexao con) {
        ItemDAO iDAO = new ItemDAO();
        return iDAO.getItens(filtro, con);
    }

    @Override
    public String toString() {
        return "{" +
            " id= '" + getId() + "'" +
            ", nome= '" + getNome() + "'" +
            ", Descrição= '" + getDescricao() + "'" +
            ", Valor= R$'" + getValor() + "'" +
            ", Quantidade= '" + getQuantidade() + "'" +"}";
    } 
}

