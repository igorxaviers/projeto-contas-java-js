package model;

import bd.dao.ServicoDAO;
import bd.util.Conexao;
import java.util.ArrayList;

public class Servico {
    private int id, quantidade;
    private String nome, descricao;
    private double valor;

    public Servico(){}
 
    public Servico(int id, String nome, String descricao,double valor, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }
    
    public Servico(String nome, String descricao,double valor, int quantidade) {
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
        ServicoDAO sDAO = new ServicoDAO();
        return sDAO.salvar(this, con);
    }

    public boolean alterar(Conexao con) {
        ServicoDAO sDAO = new ServicoDAO();
        return sDAO.alterar(this, con);
    }

    public boolean excluir(Conexao con) {
        ServicoDAO sDAO = new ServicoDAO();
        return sDAO.excluir(this.id, con);
    }

    public Servico getServico(Conexao con) {
        ServicoDAO sDAO = new ServicoDAO();
        return sDAO.getServico(this.id, con);
    }
    public ArrayList<Servico> getServicos(String filtro, Conexao con) {
        ServicoDAO sDAO = new ServicoDAO();
        return sDAO.getServicos(filtro, con);
    }

    @Override
    public String toString() {
        return "{" +
            " id= '" + getId() + "'" +
            ", nome= '" + getNome() + "'" +
            ", Descri��o= '" + getDescricao() + "'" +
            ", Valor= R$'" + getValor() + "'" +
            ", Quantidade= '" + getQuantidade() + "'" +"}";
    } 
}
