package controller;

import bd.dao.ContaDAO;
import bd.util.Banco;
import java.util.ArrayList;
import model.Pendente;
import model.Conta;
import model.Usuario;

public class PagarController extends TemplateConta
{
    
    @Override
    public boolean alterar(Conta c)
    {
        if(c.getStatus() instanceof Pendente)
            return c.alterar(Banco.getConexao());
        return false;
    }
    
    public void adicionarObservador(Conta c, Usuario u)
    {
        c.inscrever(u, u.getId(), Banco.getConexao());
    }
}
