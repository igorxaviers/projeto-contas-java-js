
package model;

import bd.util.Conexao;


public interface Sujeito 
{
    public void notificar();
    public void inscrever(Observer o, int id_usu, Conexao con);
    public void desinscrever(Observer o, int id_usu, Conexao con);
}
