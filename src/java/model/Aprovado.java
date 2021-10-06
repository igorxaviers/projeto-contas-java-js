package model;

public class Aprovado implements State{
    private final String nome = "Aprovado";

    @Override
    public State aprovar(){
        //A conta já está aprovada.
        return this;
    }
    
    @Override
    public State reprovar(){
        //Avisar que não pode reprovar porque já está aprovado.
        return this;
    }
    
    @Override
    public String getNome(){
        return nome;
    }
}
