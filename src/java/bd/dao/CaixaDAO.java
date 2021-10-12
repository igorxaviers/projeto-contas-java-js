package bd.dao;

import model.Caixa;
import bd.util.Conexao;
import java.sql.ResultSet;

public class CaixaDAO {

    public CaixaDAO() {
    }
    
    public boolean alterarSaldo (Double valor, Conexao con)
    {
        String sql = "UPDATE caixa SET valor_caixa = valor_caixa + "+ valor;
        return con.manipular(sql);                 
    }

    public double getSaldo(Conexao con)
    {   Caixa c = new Caixa();
        String sql="SELECT valor_caixa FROM caixa";
        ResultSet rs = con.consultar(sql);
        try
        {
            if (rs.next())
                c.setSaldo(rs.getDouble("valor_caixa"));
        }
        catch(Exception e){System.out.println(e);}
        return c.getSaldo();
    }

}