
package controller;

import bd.util.Banco;
import model.Aprovado;
import model.Caixa;
import model.Conta;
import model.Reprovado;

public abstract class TemplateConta {
    public abstract boolean alterar(Conta c);
    public abstract boolean excluir(Conta c);

    public final boolean adicionarConta(Conta c) {
        Caixa caixa = new Caixa(Banco.getConexao());
        boolean flag = true;

        if (c.valida())
        {
            if(c.getTipo() == 0) //Conta a pagar
            {
                if (caixa.getSaldo() > 0)
                    flag = c.salvar(Banco.getConexao());
            }
            else //Conta a receber
                flag = c.salvar(Banco.getConexao());
        }
        return flag;
    }

    public final boolean alterarConta(Conta c) {
        double valor = 0;
        Caixa caixa = new Caixa(Banco.getConexao());

        if(c.getStatus() instanceof Aprovado)
        {
            if(c.getTipo() == 0) //Conta a pagar
            {
                if (caixa.getSaldo() > 0)
                    valor = c.getValor() * -1;
            }
            else //Conta a receber
                valor = c.getValor();
                
            caixa.alterarSaldo(valor, Banco.getConexao());
        }

        return this.alterar(c);
    }

    public final boolean excluirConta(Conta c) {
        if (c.getStatus() instanceof Reprovado)
            return this.excluir(c);
        
        return false;
    }

}
