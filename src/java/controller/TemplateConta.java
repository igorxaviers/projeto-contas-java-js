
package controller;

import bd.dao.ContaDAO;
import bd.dao.ObservadorDAO;
import bd.util.Banco;
import model.Caixa;
import model.Conta;
import model.Reprovado;

public abstract class TemplateConta 
{    
    public abstract boolean alterar(Conta c);
    public abstract boolean excluir(Conta c);
    
   public final boolean adicionarConta(Conta c)
    {
        Caixa caixa = new Caixa();
        if(c.valida())
        {
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
        return false;
    }
    
    public final boolean alterarConta(Conta c)
    {
        return this.alterar(c);
    }
    
    public final boolean excluirConta(Conta c)
    {
        if(c.getStatus() instanceof Reprovado)
        {
            return this.excluir(c);
        }
        return false;
    }
    
}
