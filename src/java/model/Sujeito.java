
package model;

import bd.util.Banco;
import bd.util.Conexao;
import java.util.ArrayList;


public interface Sujeito 
{
    public void notificar();
    public void inscrever(Observer o, int id_usu, Conexao con);
    public void desinscrever(Observer o, int id_usu, Conexao con);
}
