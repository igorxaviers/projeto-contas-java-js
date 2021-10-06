
package controller;

import bd.dao.ContaDAO;
import bd.util.Banco;
import model.Caixa;
import model.Conta;
import model.Reprovado;

public abstract class TemplateConta 
{    
    public abstract boolean alterar(Conta c);
    
    public final boolean adicionarConta(Conta c)
    {
        Caixa caixa = new Caixa();
        if(caixa.getSaldo()>0)
        {
            return c.salvar(Banco.getConexao());
        }
        else
        {
           if(c.getTipo()==1)
               return c.salvar(Banco.getConexao());
           else
               return false;
        }
    }
    
    public final boolean alterarConta(Conta c)
    {
        return this.alterar(c);
    }
    
    public final boolean excluir(Conta c)
    {
        if(c.getStatus() instanceof Reprovado)
            return c.excluir(Banco.getConexao());
        return false;
    }
    
}
