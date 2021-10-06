
package model;

import bd.dao.ContaDAO;
import bd.dao.ObservadorDAO;
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
    private State status;

    //Lista de Observadores
    private ArrayList<Observer> observadores;
    
    public Conta(){}
    
    public Conta(int id,int tipo,String descricao, Date data, Date data_vencimento, double valor, Usuario usuario) 
    {
        this.descricao = descricao;
        this.tipo = tipo;
        this.id = id;
        this.data = data;
        this.data_vencimento = data_vencimento;
        this.valor = valor;
        this.usuario = usuario;
        this.status = new Pendente();
    }
    public Conta(int tipo,String descricao, Date data, Date data_vencimento, double valor, Usuario usuario) 
    {
        this.descricao = descricao;
        this.tipo = tipo;
        this.data = data;
        this.data_vencimento = data_vencimento;
        this.valor = valor;
        this.usuario = usuario;
        this.status = new Pendente();
    }

    public Conta(int id, int tipo, String descricao, Date data, Date data_vencimento, double valor, Usuario usuario, State status)
    {
        this.descricao = descricao;
        this.id = id;
        this.tipo = tipo;
        this.data = data;
        this.data_vencimento = data_vencimento;
        this.valor = valor;
        this.usuario = usuario;
        this.status = status;
    }

    
    /* ============== FUNÇÕES SUJEITO ============== */
    @Override
    public void notificar()
    {
        for (Observer observer : observadores) {
            observer.update(this); // A "atualização" passa o prórpio objeto, as classes concretas decidem o que fazer com ele
        }
    }
    public void inscrever(Observer o, int id_usu, Conexao con)
    {
        if(!this.observadores.contains(o))
        {
            this.observadores.add(o);
            new ObservadorDAO().adicionar(id_usu, id, con);
        }
            
    }
    
    public void aprovarConta()
    {
        status = status.aprovar();
    }
    
    public void reprovarConta()
    {
        status = status.reprovar();
    }
    public void desinscrever(Observer o, int id_usu, Conexao con)
    {
        int indexObservador = this.observadores.indexOf(o);
        if(indexObservador != -1)
        {
            this.observadores.remove(o);
            new ObservadorDAO().excluir(id_usu, id, con);
        }       
    }

    public void recuperarObservadores(Conexao con)
    {
        ObservadorDAO dao= new ObservadorDAO();
        observadores = dao.getObservadores(id, con);
    }
    
    public void addTodosAdmin(Conexao con)
    {
        ObservadorDAO dao = new ObservadorDAO();
        observadores = dao.addTodosAdmin(id, con);
    }
    
    /* ============== OPERAÇÕES BD ============== */
    public boolean salvar(Conexao con)
    {
        boolean flag = false;
        ContaDAO cDAO = new ContaDAO();
        if(cDAO.salvar(this, con))
        {
            flag = true;
            this.setId(con.getMaxPK("contas", "cont_id"));
        }
        return flag;
    }

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

    public State getStatus() {
        return status;
    }

    public void setStatus(State state) {
        this.status = state;
    }
    
    public State valida(String aux){
        if(aux.equalsIgnoreCase("Aprovado"))
            return new Aprovado();
        else
        {
            if(aux.equalsIgnoreCase("Reprovado"))
                return new Reprovado();
            return new Pendente();
        }     
    }
}
