package model;

import bd.dao.CaixaDAO;
import bd.util.Conexao;

public class Caixa 
{
    public double saldo;
    public boolean status;

    public Caixa(){ }

    public Caixa(Conexao con){
        getSaldo(con);
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void getSaldo(Conexao con) {
        this.saldo = new CaixaDAO().getSaldo(con);
    }

    public boolean alterarSaldo(Double valor, Conexao con) {
        if(new CaixaDAO().alterarSaldo(valor, con))
        {
            getSaldo(con);
            return true;
        }
        return false;
    }
    
    
}
