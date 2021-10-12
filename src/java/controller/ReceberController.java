package controller;

import bd.util.Banco;
import model.Conta;

public class ReceberController extends TemplateConta{

    @Override
    public boolean alterar(Conta c)
    {
        return c.alterar(Banco.getConexao());
    }

    @Override
    public boolean excluir(Conta c) 
    {
        return c.excluir(Banco.getConexao());
    }
}
