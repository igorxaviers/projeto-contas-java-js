package model;

import bd.dao.FornecedorDAO;
import bd.util.Conexao;
import java.util.ArrayList;

public class Fornecedor {
    String cnpj, razao, fantasia, endereco, bairro, email, inscricao_estadual, cep, cidade;
    Categoria categoria;

    public Fornecedor(){        
    }
    
    public Fornecedor(String cnpj, String razao, String fantasia, String endereco, String bairro, String email, String inscricao_estadual, String cep, String cidade, Categoria categoria) {
        this.cnpj = cnpj;
        this.razao = razao;
        this.fantasia = fantasia;
        this.endereco = endereco;
        this.bairro = bairro;
        this.email = email;
        this.inscricao_estadual = inscricao_estadual;
        this.cep = cep;
        this.cidade = cidade;
        this.categoria = categoria;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "cnpj=" + cnpj + ", razao=" + razao + ", fantasia=" + fantasia + ", endereco=" + endereco + ", bairro=" + bairro + ", email=" + email + ", inscricao_estadual=" + inscricao_estadual + ", cep=" + cep + ", cidade=" + cidade + ", categoria=" + categoria + '}';
    }
    
    //---------OPERAÇÕES BD
     public boolean salvar(Conexao con) {
        FornecedorDAO fDAO = new FornecedorDAO();
        return fDAO.salvar(this, con);
    }

    public boolean alterar(Conexao con) {
        FornecedorDAO fDAO = new FornecedorDAO();
        return fDAO.alterar(this, con);
    }

    public boolean excluir(Conexao con) {
        FornecedorDAO fDAO = new FornecedorDAO();
        return fDAO.excluir(this.cnpj, con);
    }

    public Fornecedor getFornecedor(Conexao con) {
        FornecedorDAO fDAO = new FornecedorDAO();
        return fDAO.getFornecedor(this.cnpj, con);
    }
    public Fornecedor getFornecedorPorRazao(Conexao con) {
        FornecedorDAO fDAO = new FornecedorDAO();
        return fDAO.getFornecedorPorRazao(this.razao, con);
    }
    public ArrayList<Fornecedor> getFornecedores(String filtro, Conexao con) {
        FornecedorDAO fDAO = new FornecedorDAO();
        return fDAO.getFornecedores(filtro, con);
    }

    public boolean valida() {
        // String cnpj, razao, fantasia, endereco, bairro, email, inscricao_estadual, cep, cidade;
        // Categoria categoria;
    
        if(cnpj.length() == 14 && !razao.isEmpty() && !fantasia.isEmpty() &&
           !endereco.isEmpty() && !bairro.isEmpty() && !email.isEmpty() &&
           !inscricao_estadual.isEmpty() && cep.length() == 8 && !cidade.isEmpty()
        )
            if(categoria != null)
                return true;
        return false;
    }
    
    
    
}
