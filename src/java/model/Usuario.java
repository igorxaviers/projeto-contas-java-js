package model;

import bd.dao.NotificarDAO;
import bd.dao.UsuarioDAO;
import bd.util.Banco;
import bd.util.Conexao;
import java.util.ArrayList;

public class Usuario implements Observer
{
    private int id;
    private String nome, login, senha;
    private boolean admin, ativo;

    public Usuario(){
        this.nome = "";
        this.login = "";
        this.senha = "";
        this.admin = false;
        this.ativo = false;
    }

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

    /* ============== FUNÇÃO Observer ============== */
    @Override
    public void update(Object novaConta) 
    {
        Conta conta = (Conta) novaConta; // Recebe o objeto de conta
        String mensagem = "O usuário "+conta.getUsuario().getNome()+" gravou uma conta de R$ "+conta.getValor()+", favor validar!";
        new NotificarDAO().salvar(this.getId(),mensagem, Banco.getConexao());
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
        return new UsuarioDAO().excluir(this.id, con);
    }
    public Usuario getUsuario(Conexao con) {
        return new UsuarioDAO().getUsuario(this.id, con);
    }
    public Usuario getUsuarioPorLogin(Conexao con) {
        return new UsuarioDAO().getUsuarioPorLogin(this.login, con);
    }
    public ArrayList<Usuario> getUsuarios(String filtro, Conexao con) {
        return new UsuarioDAO().getUsuarios(filtro, con);
    }
    public ArrayList<String> getNotificacoes(Conexao con) {
        return new UsuarioDAO().getNotificacoes(id, con);
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
