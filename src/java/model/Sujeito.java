
package model;

import bd.util.Banco;
import java.util.ArrayList;


public interface Sujeito 
{
    public void notificar();
    public void inscrever(Observer o);
    public void desinscrever(Observer o);

}
