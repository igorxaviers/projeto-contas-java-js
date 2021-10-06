package controller;

import bd.dao.ContaDAO;
import bd.util.Banco;
import model.Conta;
import model.Usuario;

public class ReceberController extends TemplateConta{

    @Override
    public boolean alterar(Conta c)
    {
        return c.alterar(Banco.getConexao());
    }
}
