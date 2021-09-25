package model;

import bd.dao.UsuarioDAO;
import bd.util.Conexao;
import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nome, login, senha;
    private boolean admin, ativo;

    public Usuario(){}

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    
    public Usuario(int id, String nome, String login, String senha, boolean admin, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.admin = admin;
        this.ativo = ativo;
    }
    
    public Usuario(String nome, String login, String senha, boolean admin, boolean ativo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.admin = admin;
        this.ativo = ativo;
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

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /* ============== OPERAÇÕES BD ============== */

    public boolean salvar(Conexao con) {
        UsuarioDAO uDAO = new UsuarioDAO();
        return uDAO.salvar(this, con);
    }

    public boolean alterar(Conexao con) {
        UsuarioDAO uDAO = new UsuarioDAO();
        return uDAO.alterar(this, con);
    }

    public boolean validar(Conexao con) {
        UsuarioDAO uDAO = new UsuarioDAO();
        Usuario aux = uDAO.validaLogin(this.login, this.senha, con);
        if(aux != null)
        {
            this.id = aux.getId();
            this.nome = aux.getNome();
            this.admin = aux.isAdmin();
            this.ativo = aux.isAtivo();
            return true;
        }
        return false;
    }

    public boolean excluir(Conexao con) {
        UsuarioDAO uDAO = new UsuarioDAO();
        return uDAO.excluir(this.id, con);
    }

    public Usuario getUsuario(Conexao con) {
        UsuarioDAO uDAO = new UsuarioDAO();
        return uDAO.getUsuario(this.id, con);
    }
    public Usuario getUsuarioPorEmail(Conexao con) {
        UsuarioDAO uDAO = new UsuarioDAO();
        return uDAO.getUsuarioPorLogin(this.login, con);
    }
    public ArrayList<Usuario> getUsuarios(String filtro, Conexao con) {
        UsuarioDAO uDAO = new UsuarioDAO();
        return uDAO.getUsuarios(filtro, con);
    }

    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", login='" + getLogin() + "'" +
            ", senha='" + getSenha() + "'" +
            ", admin='" + isAdmin() + "'" +
            ", ativo='" + isAtivo() + "'" +
            "}";
    }

 
    
}
