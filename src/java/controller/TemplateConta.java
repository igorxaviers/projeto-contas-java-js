
package controller;

import bd.util.Banco;
import model.Caixa;
import model.Conta;

public abstract class TemplateConta 
{
    abstract String salvar(Conta c);//SALVAR DO PAGAR (AVISAR OS OBSEERVERS) - SALVAR DO RECEBER ()
    
    public final String adicionarConta(Conta c)
    {
        Caixa caixa = new Caixa();
        if(caixa.getSaldo()>0)
        {
            return this.salvar(c);
        }
        else
        {
           if(c.getTipo()==1)
               return this.salvar(c);
           else
               return "";
        }
    } 
    
}
