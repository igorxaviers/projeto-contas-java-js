package controller;

import bd.dao.ObservadorDAO;
import bd.util.Banco;
import model.Pendente;
import model.Conta;
import model.Usuario;

public class PagarController extends TemplateConta
{
    
    @Override
    public boolean alterar(Conta c)
    {
        Conta aux = new Conta();
        aux.setId(c.getId()); 
        if(aux.getConta(Banco.getConexao()).getStatus() instanceof Pendente) //Buscar no banco o Status atual da conta para altera-la
            return c.alterar(Banco.getConexao());
        return false;
    }
    
    public void adicionarObservador(Conta c, Usuario u)
    {
        c.inscrever(u, u.getId(), Banco.getConexao());
    }

    @Override
    public boolean excluir(Conta c) 
    {        
        if(new ObservadorDAO().excluirCascata(c.getId(), Banco.getConexao()))
            return c.excluir(Banco.getConexao());
        return false;
    }
    
}
