
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
        boolean flag = true;

        if(c.valida())
        {
            if(c.getStatus() instanceof Aprovado) //Se a conta for aprovada, será retirado do saldo o valor da conta
            {
                if(c.getTipo() == 0) //Conta a pagar
                {
                    if (caixa.getSaldo() > 0) //Verifica se há saldo suficiente antes de salvar conta a PAgar
                        valor = c.getValor() * -1; //Transforma valor em negativo para retirar do saldo
                }
                else //Conta a receber
                    valor = c.getValor();
                flag = caixa.alterarSaldo(valor, Banco.getConexao()); //Alterar o valor do saldo com base na conta Pagar(-) / Receber(+)
            }
            flag = this.alterar(c);
        }
        return flag;

    }

    public final boolean excluirConta(Conta c) {
        if (c.getStatus() instanceof Reprovado)
            return this.excluir(c);
        
        return false;
    }

}
