
package model;

import bd.dao.ContaDAO;
import bd.util.Banco;
import bd.util.Conexao;
import java.util.ArrayList;
import java.util.Date;

public class Conta implements Sujeito
{
    private String descricao;
    private int id, tipo;//TIPO PAGAR = 0, RECEBER = 1
    private Date data, data_vencimento;
    private double valor;
    private Usuario usuario;
    private boolean aprovada;
    //Lista de Observadores
    private ArrayList<Observer> observadores;
    
    public Conta(){}
    
    public Conta(int id,int tipo,String descricao, Date data, Date data_vencimento, double valor, Usuario usuario, boolean aprovada) 
    {
        this.descricao = descricao;
        this.tipo = tipo;
        this.id = id;
        this.data = data;
        this.data_vencimento = data_vencimento;
        this.valor = valor;
        this.usuario = usuario;
        this.aprovada = aprovada;
        //CRIAR A LISTA DE OBSERVADORES AQUI NO CONSTRUTOR
    }

    /* ============== FUNÇÕES SUJEITO ============== */
    @Override
    public void notificar()
    {
        for (Observer observer : observadores) {
            observer.update(this); // A "atualização" passa o prórpio objeto, as classes concretas decidem o que fazer com ele
        }
    }
    public void inscrever(Observer o)
    {
        if(!this.observadores.contains(o))
            this.observadores.add(o);
    }
    public void desinscrever(Observer o)
    {
        int indexObservador = this.observadores.indexOf(o);
        if(indexObservador != -1)
            this.observadores.remove(o);
    }

    /* ============== OPERAÇÕES BD ============== */
    public boolean salvar(Conexao con)
    {
        ContaDAO cDAO = new ContaDAO();
        if(cDAO.salvar(this, con)) // Se salvar corretamente a conta
        {
            this.notificar(); // Notifica os observadores da nova conta cadastrada
            return true; // Savlou corretamente
        }
        return false; // Não salvou corretamente
    }

    // public ArrayList<Usuario> getObservadores() {
    //     return observadores;
    // }
    
    public boolean alterar (Conexao con)
    {
        return new ContaDAO().alterar(this, con);
    }
    
    public boolean excluir (Conexao con)
    {
        return new ContaDAO().excluir(id, con);
    }
    
    public Conta getConta(Conexao con)
    {
        return new ContaDAO().getConta(id, con);
    }  
    
    public ArrayList<Conta> getContas(String filtro, Conexao con) {
        return new ContaDAO().getContas(filtro, con);
    }

    /* =============================================== */

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAprovada() {
        return aprovada;
    }

    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }
}
