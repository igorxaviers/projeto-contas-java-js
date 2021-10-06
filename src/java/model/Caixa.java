
package model;


public class Caixa 
{
    public double saldo;
    public boolean status;

    public Caixa() 
    {
        this.saldo = 1000;
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
    
    
}
