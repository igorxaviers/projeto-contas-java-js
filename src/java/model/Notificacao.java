package model;

import bd.dao.NotificacaoDAO;
import bd.dao.UsuarioDAO;
import bd.util.Conexao;
import java.util.ArrayList;
import java.util.Date;

public class Notificacao {
    private Usuario usuario;
    private String mensagem;
    private Date data;

    public Notificacao(){}
    
    public Notificacao(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Notificacao(Usuario usuario, String mensagem, Date data) {
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.data = data;
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public Date getData() {
        return data;
    }
    
    public ArrayList<Notificacao> getNotificacoes(Conexao con) {
        return new NotificacaoDAO().getNotificacoes(this.usuario.getId(), con);
    }
}
